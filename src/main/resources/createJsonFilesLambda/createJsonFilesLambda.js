const AWS = require('aws-sdk');
const https = require('https');

/**
 * @typedef {import("aws-lambda").Context} Context
 * @typedef {import("aws-lambda").Callback} Callback
 * @typedef {{ action: string }} Event
 * @typedef {Event & { numberOfFiles: number; offset: number; prefix: string; bucket: string; }} CreateEvent
 * @typedef {Event & { bucket: string }} DeleteAllEvent
 * @typedef {Event & { bucket: string, objects: string[] }} DeleteListEvent
 * @typedef {() => Promise<unknown>} AsyncFunction
 */

/** Action for creating JSON files */
const ACTION_CREATE = 'create';
/** Action for deleting all objects from a bucket */
const ACTION_DELETE_ALL = 'delete';
/** Action for deleting a list of objects from a bucket */
const ACTION_DELETE_LIST = 'deleteList';

const agent = new https.Agent({
    keepAlive: true,
    maxSockets: 100
});

/**
 * Create a new S3 client.
 *
 * @returns {AWS.S3} a new S3 client
 */
function getS3Client() {
    return new AWS.S3({
        httpOptions: {
            agent
        }
    });
}

// Formatter adds space after "async"
// eslint-disable-next-line space-before-function-paren
exports.handler = async (/** @type {Event} */ event, /** @type {Context} */ context) => {
    if (event.action === ACTION_CREATE) {
        await handleCreate(/** @type {CreateEvent} */(event), context);
    } else if (event.action === ACTION_DELETE_ALL) {
        await handleDelete(/** @type {DeleteAllEvent} */(event), context);
    } else if (event.action === ACTION_DELETE_LIST) {
        await handleDeleteList(/** @type {DeleteListEvent} */(event), context);
    } else {
        throw Error(`Unknown action '${event.action}'`);
    }
};

/**
 * Executes the given function at must three times with 10s delay.
 *
 * @param {AsyncFunction} func the function to execute with retry
 */
async function doWithRetry(func) {
    let retryCounter = 0;
    // eslint-disable-next-line no-constant-condition
    while (true) {
        try {
            await func();
            break;
        } catch (error) {
            if (retryCounter < 3) {
                await delay(10000);
                console.log(`Operation failed after ${retryCounter} retries: " ${error}`);
                retryCounter++;
            } else {
                throw error;
            }
        }
    }
}

/**
 * @param {CreateEvent} event the event
 * @param {Context} context the lambda context
 */
async function handleCreate(event, context) {
    const s3 = getS3Client();
    /** @type {Promise<unknown>[]} */
    const promises = [];
    const numberOfFiles = event.numberOfFiles;
    console.log(`Creating ${numberOfFiles} files...`);
    for (let i = 0; i < numberOfFiles; i++) {
        const fileId = event.offset + i;
        const key = event.prefix + fileId + '.json';
        const data = {
            id: fileId,
            name: randomString(20)
        };
        const jsonData = JSON.stringify(data);
        const params = {
            Bucket: event.bucket,
            Key: key,
            Body: jsonData
        };
        promises.push(doWithRetry(() => s3.upload(params).promise()));
        await delay(10);
    }
    console.log('Waiting for creating to finish...');
    try {
        await Promise.all(promises);
    } catch (error) {
        context.fail('failed to create s3 object: ' + error);
    }
}

/**
 * @param {DeleteAllEvent} event the event
 * @param {Context} context the lambda context
 */
async function handleDelete(event, context) {
    const s3 = getS3Client();
    const lambdaClient = new AWS.Lambda();
    /** @type {Promise<unknown>[]} */
    const promises = [];
    let objectsPage = await s3.listObjectsV2({ Bucket: event.bucket }).promise();
    let totalObjectCount = 0;

    // eslint-disable-next-line no-constant-condition
    while (true) {
        const objects = [];
        for (const object of objectsPage.Contents) {
            objects.push(object.Key);
        }
        totalObjectCount += objects.length;
        const callParams = JSON.stringify({
            action: ACTION_DELETE_LIST,
            bucket: event.bucket,
            objects
        });
        console.log(`Calling lambda to delete ${objects.length} objects`);
        promises.push(lambdaClient.invoke({ FunctionName: context.functionName, Payload: callParams }).promise());
        await delay(5);
        if (objectsPage.NextContinuationToken) {
            objectsPage = await s3.listObjectsV2({
                Bucket: event.bucket,
                ContinuationToken: objectsPage.NextContinuationToken
            }).promise();
        } else {
            break;
        }
    }
    console.log(`Waiting for ${promises.length} lambdas to finish deleting ${totalObjectCount} objects...`);
    try {
        await Promise.all(promises);
        console.log(`Deleted ${totalObjectCount} objects`);
    } catch (error) {
        context.fail('failed start delete-files lambda: ' + error);
        throw error;
    }
}

/**
 * @param {DeleteListEvent} event the event
 * @param {Context} context the lambda context
 */
async function handleDeleteList(event, context) {
    const s3 = getS3Client();
    /** @type {Promise<unknown>[]} */
    const promises = [];
    console.log(`Deleting ${event.objects.length} objects...`);
    for (const key of event.objects) {
        const params = { Bucket: event.bucket, Key: key };
        promises.push(doWithRetry(() => s3.deleteObject(params).promise()));
        await delay(5);
    }
    try {
        await Promise.all(promises);
    } catch (error) {
        context.fail('failed to delete objects: ' + error);
        throw error;
    }
}

/**
 * @param {number} length length of the random string
 * @returns {string} a random string of the given length
 */
function randomString(length) {
    const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let result = '';
    for (let i = 0; i < length; i++) {
        result += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    return result;
}

/**
 * @param {number} ms delay in milliseconds
 * @returns {Promise<void>} promise
 */
function delay(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}
