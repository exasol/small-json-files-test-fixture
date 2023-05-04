import { InvokeCommand, Lambda, LogType } from '@aws-sdk/client-lambda';
import { DeleteObjectsCommand, ListObjectsV2Command, PutObjectCommand, S3Client } from '@aws-sdk/client-s3';

/**
 * @typedef {import("aws-lambda").Context} Context
 * @typedef {import("aws-lambda").Callback} Callback
 * @typedef {{ action: string }} Event
 * @typedef {Event & { numberOfFiles: number; offset: number; prefix: string; bucket: string; }} CreateEvent
 * @typedef {Event & { bucket: string }} DeleteAllEvent
 * @typedef {Event & { bucket: string, objects: string[] }} DeleteListEvent
 * @typedef {import('@aws-sdk/client-s3')._Object} S3Object
 */

/** Action for creating JSON files */
const ACTION_CREATE = 'create';
/** Action for deleting all objects from a bucket */
const ACTION_DELETE_ALL = 'delete';
/** Action for deleting a list of objects from a bucket */
const ACTION_DELETE_LIST = 'deleteList';
/**
 * The deleteObjects() request must contain 1,000 or less objects, else it will fail with the following error
 * message:
 *
 * The XML you provided was not well-formed or did not validate against our published schema
 */
const MAX_COUNT_PER_DELETE_REQUEST = 1000;

const s3 = new S3Client({});
const lambdaClient = new Lambda({});

/**
 * Entry point for the Lambda.
 * @param {Event}  event the lambda event
 * @param {Context} context the lambda context
 */
export async function handler(event, context) {
    if (event.action === ACTION_CREATE) {
        await handleCreate(/** @type {CreateEvent} */(event), context);
    } else if (event.action === ACTION_DELETE_ALL) {
        await handleDelete(/** @type {DeleteAllEvent} */(event), context);
    } else if (event.action === ACTION_DELETE_LIST) {
        await handleDeleteList(/** @type {DeleteListEvent} */(event), context);
    } else {
        throw Error(`Unknown action '${event.action}'`);
    }
}

/**
 * Executes the given function at must three times with 10s delay.
 * @param {string} operation name of the operation to perform. This is used in log messages.
 * @param {() => Promise<unknown>} func the function to execute with retry
 */
async function doWithRetry(operation, func) {
    let retryCounter = 0;
    // eslint-disable-next-line no-constant-condition
    while (true) {
        try {
            await func();
            break;
        } catch (error) {
            console.log(`Operation ${operation} failed after ${retryCounter} retries: ${error}`);
            if (retryCounter < 3) {
                await delay(10000);
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
    /** @type {Promise<unknown>[]} */
    const promises = [];
    const numberOfFiles = event.numberOfFiles;
    console.log(`Creating ${numberOfFiles} files in bucket '${event.bucket}'...`);
    for (let i = 0; i < numberOfFiles; i++) {
        const fileId = event.offset + i;
        const key = event.prefix + fileId + '.json';
        const content = generateJsonContent(fileId);
        promises.push(uploadFile(event.bucket, key, content));
        await delay(10);
    }
    console.log('Waiting for creating to finish...');
    try {
        await Promise.all(promises);
    } catch (error) {
        context.fail('failed to create s3 object: ' + error);
    }
    console.log('Creating finished.');
}

/**
 * Upload an object to S3.
 * @param {string} bucket  the bucket name
 * @param {string} key the object name
 * @param {string} content the object content
 * @returns {Promise<unknown>} the upload result
 */
async function uploadFile(bucket, key, content) {
    const command = new PutObjectCommand({
        Bucket: bucket,
        Key: key,
        Body: content
    });
    return doWithRetry(`Upload file #${key}`, () => s3.send(command));
}

/**
 * Delete all objects in the given bucket.
 * @param {DeleteAllEvent} event the event
 * @param {Context} context the lambda context
 */
async function handleDelete(event, context) {
    const allObjects = await fetchObjects(event.bucket);
    if (allObjects.length === 0) {
        console.log(`No objects to delete from bucket ${event.bucket}`);
        return;
    }
    console.log(`Found ${allObjects.length} objects in ${event.bucket}`);
    const chunks = splitIntoChunks(allObjects, Math.min(1, allObjects.length / 20000));
    /** @type {Promise<unknown>[]} */
    const promises = [];
    for (const chunk of chunks) {
        if (chunk.length > 0) {
            promises.push(invokeDeleteLambda(context, event.bucket, chunk));
            await delay(5);
        } else {
            console.log('Chunk is empty, nothing to delete');
        }
    }
    console.log(`Waiting for ${promises.length} lambdas to finish deleting ${allObjects.length} objects...`);
    try {
        await Promise.all(promises);
        console.log(`Deleted ${allObjects.length} objects`);
    } catch (error) {
        context.fail('failed start delete-files lambda: ' + error);
        throw error;
    }
}

/**
 * Invoke the lambda to delete the given S3 objects.
 * @param {Context} context the lambda context
 * @param {string} bucket the S3 bucket
 * @param {string[]} objects the objects to delete
 * @returns {Promise<unknown>} the invocation result
 */
async function invokeDeleteLambda(context, bucket, objects) {
    /** @typedef {DeleteListEvent} */
    const callParams = {
        action: ACTION_DELETE_LIST,
        bucket,
        objects
    };
    console.log(`Calling lambda to delete ${objects.length} objects`);
    const command = new InvokeCommand({
        FunctionName: context.functionName,
        Payload: new TextEncoder().encode(JSON.stringify(callParams)),
        LogType: LogType.Tail
    });
    return lambdaClient.send(command);
}

/**
 * Split the given array into chunks.
 * @param {string[]} array the array to split
 * @param {number} chunkSize the chunk size
 * @returns {string[][]} the chunks
 */
function splitIntoChunks(array, chunkSize) {
    const result = [];
    for (let i = 0; i < array.length; i += chunkSize) {
        const chunk = array.slice(i, i + chunkSize);
        result.push(chunk);
    }
    return result;
}

/**
 * Fetch all S3 objects from a bucket.
 * @param {string} bucket the S3 bucket
 * @returns {Promise<string[]>} the keys of all objects
 */
async function fetchObjects(bucket) {
    /** @typedef {string[]} */
    const objects = [];
    /**
     * Fetch S3 objects using the given pagination token.
     * @param {string} bucket the S3 bucket
     * @param {string} continuationToken the pagination token
     * @returns {Promise<string[]>} the keys of all objects
     */
    async function fetchObjectsWithPagination(bucket, continuationToken) {
        const result = await s3.send(
            new ListObjectsV2Command({
                Bucket: bucket,
                ContinuationToken: continuationToken
            })
        );
        objects.push(...(result.Contents || []).map(object => object.Key));
        if (result.NextContinuationToken) {
            return fetchObjectsWithPagination(bucket, result.NextContinuationToken);
        }
    }
    await fetchObjectsWithPagination(bucket, undefined);
    return objects;
}

/**
 * Delete a list of keys from an S3 bucket.
 * @param {DeleteListEvent} event the event
 * @param {Context} context the lambda context
 */
async function handleDeleteList(event, context) {
    /** @type {Promise<unknown>[]} */
    const promises = [];
    const chunks = splitIntoChunks(event.objects, MAX_COUNT_PER_DELETE_REQUEST);
    console.log(`Deleting ${event.objects.length} objects in ${chunks.length}...`);
    for (const chunk of chunks) {
        const objectIdentifiers = chunk.map(key => { return { Key: key }; });
        const command = new DeleteObjectsCommand({ Bucket: event.bucket, Delete: { Objects: objectIdentifiers } });
        promises.push(doWithRetry(`Delete ${chunk.length} files`, () => s3.send(command)));
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
 * Generate JSON file content with random content.
 * @param {number} id id for the file content
 * @returns {string} content in JSON format
 */
function generateJsonContent(id) {
    const data = {
        id,
        name: randomString(20)
    };
    return JSON.stringify(data);
}

/**
 * Generate a random string of the given length.
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
 * Return a promise that waits for the given delay.
 * @param {number} ms delay in milliseconds
 * @returns {Promise<void>} promise that waits for the given delay
 */
function delay(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}
