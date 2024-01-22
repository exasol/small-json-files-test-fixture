import { InvokeCommand, Lambda, LogType } from '@aws-sdk/client-lambda';
import { DeleteObjectsCommand, ListObjectsV2Command, PutObjectCommand, S3Client } from '@aws-sdk/client-s3';

/**
 * @typedef {import("aws-lambda").Context} Context
 * @typedef {import("aws-lambda").Callback} Callback
 * @typedef {{ action: "create", numberOfFiles: number, offset: number, prefix: string, bucket: string }} CreateEvent
 * @typedef {{ action: "delete", bucket: string }} DeleteAllEvent
 * @typedef {{ action: "deleteList", bucket: string, files: string[] }} DeleteListEvent
 * @typedef { CreateEvent | DeleteAllEvent | DeleteListEvent } Event
 */

/** Action for creating JSON files */
const ACTION_CREATE = 'create';
/** Action for deleting all files from a bucket */
const ACTION_DELETE_ALL = 'delete';
/** Action for deleting a list of files from a bucket */
const ACTION_DELETE_LIST = 'deleteList';
/**
 * The DeleteObjectsCommand request must contain 1,000 or less files, else it will fail with the following error
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
 * @returns {Promise<string>} result of the operation
 */
export async function handler(event, context) {
    const action = event.action;
    if (event.action === ACTION_CREATE) {
        return await handleCreate(event);
    } else if (event.action === ACTION_DELETE_ALL) {
        return await handleDelete(event, context);
    } else if (event.action === ACTION_DELETE_LIST) {
        return await handleDeleteList(event);
    } else {
        throw Error(`Unknown action '${action}'`);
    }
}

/**
 * Executes the given function at most three times with 10s delay.
 * @template T result type
 * @param {string} operation name of the operation to perform. This is used in log messages.
 * @param {() => Promise<T>} func the function to execute with retry
 * @returns {Promise<T>} result
 */
async function doWithRetry(operation, func) {
    let retryCounter = 0;
    // eslint-disable-next-line no-constant-condition
    while (true) {
        try {
            return await func();
        } catch (error) {
            console.log(`Operation '${operation}' failed after ${retryCounter} retries: ${error}`);
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
 * @returns {Promise<string>} result message
 */
async function handleCreate(event) {
    /** @type {Promise<boolean>[]} */
    const promises = [];
    const numberOfFiles = event.numberOfFiles;
    console.log(`Creating ${numberOfFiles} files in bucket '${event.bucket}'...`);
    for (let i = 0; i < numberOfFiles; i++) {
        const fileId = event.offset + i;
        const key = generateFilename(event.prefix, fileId);
        const content = generateJsonContent(fileId);
        promises.push(uploadFile(event.bucket, key, content));
        await delay(10);
    }
    console.log('Waiting for creating to finish...');
    try {
        const allResults = await Promise.all(promises);
        if (allResults.length !== numberOfFiles) {
            throw new Error(`Expected ${numberOfFiles} results but got ${allResults.length}`);
        }
        const failedResults = allResults.filter(r => !r).length;
        if (failedResults > 0) {
            throw new Error(`${failedResults} lambdas failed`);
        }
        const result = `Creating ${numberOfFiles} files finished.`;
        console.log(result);
        return result;
    } catch (error) {
        console.error(`Failed to create s3 files: ${error}`);
        throw error;
    }
}

/**
 * Generates a file name with a seemingly random prefix that improves scaling by allowing S3 to partition the files.
 * @param {string} prefix file name prefix, e.g. "test-data-"
 * @param {number} id the file ID
 * @returns {string} the file name
 */
function generateFilename(prefix, id) {
    const prefixedId = id.toString().padStart(7, '0');
    const hash = reverseString(prefixedId);
    return `${prefix}${hash}-${id}.json`;
}

/**
 * @param {string} str the string to reverse
 * @returns {string} the reversed input string
 */
function reverseString(str) {
    return str.split('').reverse().join('');
}

/**
 * Upload a file to S3.
 * @param {string} bucket  the bucket name
 * @param {string} key the file name
 * @param {string} content the file content
 * @returns {Promise<boolean>} the upload result
 */
async function uploadFile(bucket, key, content) {
    const command = new PutObjectCommand({
        Bucket: bucket,
        Key: key,
        Body: content
    });

    /**
     * Perform the upload.
     * @returns {Promise<boolean>} `true` if the the action was successful
     */
    async function upload() {
        await s3.send(command);
        return true;
    }
    return doWithRetry(`Upload file ${key}`, upload);
}

/**
 * Delete all files in the given bucket.
 * @param {DeleteAllEvent} event the event
 * @param {Context} context the lambda context
 * @returns {Promise<string>} result message
 */
async function handleDelete(event, context) {
    let continuationToken;
    /** @type {string[]} */
    let filesToDelete = [];
    /** @type {Promise<string>[]} */
    const promises = [];
    let totalFileCount = 0;
    console.log(`Listing files in bucket ${event.bucket}...`);
    let lambdaCount = 0;
    do {
        const result = await listFiles(event.bucket, continuationToken);
        continuationToken = result.continuationToken;
        totalFileCount += result.files.length;
        filesToDelete.push(...result.files);
        if (filesToDelete.length >= 50000) {
            console.log(`Calling lambda #${lambdaCount++} to delete ${filesToDelete.length} files, total file count: ${totalFileCount}`);
            promises.push(invokeDeleteLambda(context, event.bucket, filesToDelete));
            await delay(10);
            filesToDelete = [];
        }
    } while (continuationToken);
    if (filesToDelete.length > 0) {
        console.log(`Calling last lambda to delete ${filesToDelete.length} files, total file count: ${totalFileCount}`);
        promises.push(invokeDeleteLambda(context, event.bucket, filesToDelete));
    }
    console.log(`Waiting for ${promises.length} lambdas to finish deleting ${totalFileCount} files...`);
    try {
        const lambdaResults = await Promise.all(promises);
        const result = `Deleted ${totalFileCount} files using ${lambdaResults.length} lambdas`;
        console.log(result);
        return result;
    } catch (error) {
        console.error(`Failed to delete lambdas: ${error}`);
        throw error;
    }
}

/**
 * Invoke the lambda to delete the given S3 files.
 * @param {Context} context the lambda context
 * @param {string} bucket the S3 bucket
 * @param {string[]} files the files to delete
 * @returns {Promise<string>} the invocation result
 */
async function invokeDeleteLambda(context, bucket, files) {
    /** @typedef {DeleteListEvent} */
    const callParams = {
        action: ACTION_DELETE_LIST,
        bucket,
        files
    };
    const command = new InvokeCommand({
        FunctionName: context.functionName,
        Payload: new TextEncoder().encode(JSON.stringify(callParams)),
        LogType: LogType.None,
        InvocationType: 'RequestResponse' // 'Event' will cause the following error:
        // Runtime.UnhandledPromiseRejection: RequestEntityTooLargeException: 1594530 byte payload is too large for the Event invocation type (limit 262144 bytes)
    });
    const result = await lambdaClient.send(command);

    let payload = '<no payload>';
    if (result.Payload) {
        payload = result.Payload.transformToString('utf-8');
    }
    console.log(`Invoke lambda result: status ${result.StatusCode}, error: ${result.FunctionError}, payload: ${payload}`);
    return payload;
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
 * List S3 files using the given pagination token.
 * @param {string} bucket the S3 bucket
 * @param {string} continuationToken the pagination token
 * @returns {Promise<{files:string[], continuationToken?:string}>} the keys of all files
 */
async function listFiles(bucket, continuationToken) {
    const result = await s3.send(
        new ListObjectsV2Command({
            Bucket: bucket,
            ContinuationToken: continuationToken
        })
    );
    const files = (result.Contents || []).map(file => file.Key);
    return { files, continuationToken: result.NextContinuationToken };
}

/**
 * Delete a list of keys from an S3 bucket.
 * @param {DeleteListEvent} event the event
 * @returns {Promise<string>} result message
 */
async function handleDeleteList(event) {
    /** @type {Promise<unknown>[]} */
    const promises = [];
    const chunks = splitIntoChunks(event.files, MAX_COUNT_PER_DELETE_REQUEST);
    console.log(`Deleting ${event.files.length} files in ${chunks.length} chunks with ${MAX_COUNT_PER_DELETE_REQUEST} files each...`);
    for (const chunk of chunks) {
        const identifiers = chunk.map(key => { return { Key: key }; });
        const command = new DeleteObjectsCommand({ Bucket: event.bucket, Delete: { Objects: identifiers } });
        promises.push(doWithRetry(`Delete ${chunk.length} files`, () => s3.send(command)));
        await delay(5);
    }
    await Promise.all(promises);
    const status = `Deleted ${event.files.length} files in ${chunks.length} chunks`;
    console.log(status);
    return status;
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
