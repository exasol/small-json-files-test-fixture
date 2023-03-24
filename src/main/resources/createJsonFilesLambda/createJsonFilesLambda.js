const AWS = require('aws-sdk');
const https = require('https');

/**
 * @typedef {import("aws-lambda").Context} Context
 * @typedef {import("aws-lambda").Callback} Callback
 * @typedef {{ action: string }} Event
 * @typedef {{ numberOfFiles: any; offset: number; prefix: any; bucket: any; } & Event} CreateEvent
 * @typedef {{ bucket: string } & Event} DeleteAllEvent
 * @typedef {{ bucket: string, objects: string[] } & Event} DeleteListEvent
 */

/** Action for creating JSON files */
const ACTION_CREATE = "create";
/** Action for deleting all objects from a bucket */
const ACTION_DELETE_ALL = "delete";
/** Action for deleting a list of objects from a bucket */
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

exports.handler = async (/** @type Event */ event, /** @type Context */ context, /** Callback */ callback) => {
    if (event.action === ACTION_CREATE) {
        await handleCreate(/** @type {CreateEvent} */(event), context);
    } else if (event.action === ACTION_DELETE_ALL) {
        await handleDelete(/** @type {DeleteAllEvent} */(event), context);
    } else if (event.action === ACTION_DELETE_LIST) {
        await handleDeleteList(/** @type {DeleteListEvent} */(event), context);
    } else {
        throw Error(`Unknown action '${event.action}'`)
    }
};

/**
 * Executes the given function at must three times with 10s delay.
 * @param { () => Promise<unknown> } func
 */
async function doWithRetry(func) {
    let retryCounter = 0;
    while (true) {
        try {
            await func();
            break;
        } catch (exception) {
            if (retryCounter < 3) {
                await delay(10000);
                console.log(`Operation failed after ${retryCounter} retries: " ${exception}`);
                retryCounter++;
            } else {
                throw exception;
            }
        }
    }
}

/**
 * @param {CreateEvent} event
 * @param {Context} context
 */
async function handleCreate(event, context) {
    const s3 = getS3Client();
    let promises = [];
    const numberOfFiles = event.numberOfFiles;
    console.log(`Creating ${numberOfFiles} files...`);
    for (let i = 0; i < numberOfFiles; i++) {
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
    console.log("Waiting for creating to finish...")
    try {
        await Promise.all(promises)
    } catch (exception) {
        context.fail("failed to create s3 object: " + exception)
    }
}

/**
 * @param {DeleteAllEvent} event
 * @param {Context} context
 */
async function handleDelete(event, context) {
    const s3 = getS3Client();
    const lambdaClient = new AWS.Lambda();
    let promises = [];
    let objectsPage = await s3.listObjectsV2({ Bucket: event.bucket }).promise();
    let totalObjectCount = 0;
    while (true) {
        let objects = []
        for (let object of objectsPage.Contents) {
            objects.push(object.Key);
        }
        totalObjectCount += objects.length;
        const callParams = JSON.stringify({
            action: ACTION_DELETE_LIST,
            bucket: event.bucket,
            objects: objects
        })
        console.log(`Calling lambda to delete ${objects.length} objects`)
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
    console.log(`Waiting for ${promises.length} lambdas to finish deleting ${totalObjectCount} objects...`)
    try {
        await Promise.all(promises);
        console.log(`Deleted ${totalObjectCount} objects`);
    } catch (exception) {
        context.fail("failed start delete-files lambda: " + exception);
        throw exception;
    }
}

/**
 * @param {DeleteListEvent} event
 * @param {Context} context
 */
async function handleDeleteList(event, context) {
    const s3 = getS3Client();
    let promises = [];
    console.log(`Deleting ${event.objects.length} objects...`)
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

/**
 * @param {number} length
 */
function randomString(length) {
    let chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let result = '';
    for (let i = 0; i < length; i++) {
        result += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    return result;
}

/**
 * @param {number} ms
 */
function delay(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}
