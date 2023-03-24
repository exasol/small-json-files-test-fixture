const AWS = require('aws-sdk');
const https = require('https');

const ACTION_DELETE_LIST = "deleteList";

const agent = new https.Agent({
    keepAlive: true,
    maxSockets: 100
});

function getS3Client() {
    return new AWS.S3({
        httpOptions: {
            agent
        }
    });
}

exports.handler = async (event, context) => {
    if (event.action === "create") {
        await handleCreate(event, context);
    } else if (event.action === "delete") {
        await handleDelete(event, context);
    } else if (event.action === ACTION_DELETE_LIST) {
        await handleDeleteList(event, context);
    } else {
        throw Error("Unknown action '" + event.action + "'")
    }
};


async function doWithRetry(func) {
    let retryCounter = 0;
    while (true) {
        try {
            await func();
            break;
        } catch (exception) {
            if (retryCounter < 3) {
                await delay(10000);
                console.log("operation failed: " + exception);
                console.log("retrying");
                retryCounter++;
            } else {
                throw exception;
            }
        }
    }
}

async function handleCreate(event, context) {
    const s3 = getS3Client();
    let promises = []
    for (let i = 0; i < event.numberOfFiles; i++) {
        const fileId = event.offset + i;
        const key = event.prefix + fileId + ".json";
        const data = {
            id: fileId,
            name: randomString(20)
        };
        const json_data = JSON.stringify(data)
        const params = {
            Bucket: event.bucket,
            Key: key,
            Body: json_data
        };
        promises.push(doWithRetry(() => s3.upload(params).promise()));
        await delay(10)
    }
    try {
        await Promise.all(promises)
    } catch (exception) {
        context.fail("failed to create s3 object: " + exception)
    }
}

async function handleDelete(event, context) {
    const s3 = getS3Client();
    const lambdaClient = new AWS.Lambda();
    let promises = [];
    let objectsPage = await s3.listObjectsV2({ Bucket: event.bucket }).promise();
    while (true) {
        let objects = []
        for (let object of objectsPage.Contents) {
            objects.push(object.Key);
        }
        const callParams = JSON.stringify({
            action: ACTION_DELETE_LIST,
            bucket: event.bucket,
            objects: objects
        })
        console.log("Calling lambda with params", callParams)
        promises.push(lambdaClient.invoke({ FunctionName: context.functionName, Payload: callParams }).promise());
        await delay(5)
        if (objectsPage.NextContinuationToken) {
            objectsPage = await s3.listObjectsV2({
                Bucket: event.bucket,
                ContinuationToken: objectsPage.NextContinuationToken
            }).promise();
        } else {
            break;
        }
    }
    try {
        await Promise.all(promises);
    } catch (exception) {
        context.fail("failed start delete-files lambda: " + exception);
        throw exception;
    }
}

async function handleDeleteList(event, context) {
    const s3 = getS3Client();
    let promises = [];
    console.log(`Deleting ${event.objects} objects...`)
    for (let key of event.objects) {
        const params = { Bucket: event.bucket, Key: key };
        promises.push(doWithRetry(() => s3.deleteObject(params).promise()));
        await delay(5)
    }
    try {
        await Promise.all(promises);
    } catch (exception) {
        context.fail("failed to delete objects: " + exception);
        throw exception;
    }
}

function randomString(length) {
    let chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let result = '';
    for (let i = 0; i < length; i++) {
        result += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    return result;
}

function delay(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}
