package com.panjin.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;

import java.io.File;
import java.util.List;

/**
 * @author panjin
 */
public class AWSS3Service {

    private final AmazonS3 s3client;

    public AWSS3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public AWSS3Service() {
        this(new AmazonS3Client() {
        });
    }

    /**
     * is bucket exist?
     * @param bucketName
     * @return
     */
    public boolean doesBucketExist(String bucketName) {
        return s3client.doesBucketExistV2(bucketName);
    }

    /**
     * create a bucket
     * @param bucketName
     * @return
     */
    public Bucket createBucket(String bucketName) {
        return s3client.createBucket(bucketName);
    }

    /**
     * list all buckets
     * @return
     */
    public List<Bucket> listBuckets() {
        return s3client.listBuckets();
    }

    /**
     * delete a bucket
     * @param bucketName
     */
    public void deleteBucket(String bucketName) {
        s3client.deleteBucket(bucketName);
    }

    /**
     * uploading object
     * @param bucketName
     * @param key
     * @param file
     * @return
     */
    public PutObjectResult putObject(String bucketName, String key, File file) {
        return s3client.putObject(bucketName, key, file);
    }

    /**
     * uploading object
     * @param bucketName
     * @param key
     * @param content
     * @return
     */
    public PutObjectResult putObject(String bucketName, String key, String content) {
        return s3client.putObject(bucketName, key, content);
    }

    /**
     * listing objects
     * @param bucketName
     * @return
     */
    public ObjectListing listObjects(String bucketName) {
        return s3client.listObjects(bucketName);
    }

    /**
     * listing objects
     * @param bucketName
     * @param prefix
     * @return
     */
    public ObjectListing listObjects(String bucketName, String prefix) {
        return s3client.listObjects(bucketName, prefix);
    }

    /**
     * get an object
     * @param bucketName
     * @param objectKey
     * @return
     */
    public S3Object getObject(String bucketName, String objectKey) {
        return s3client.getObject(bucketName, objectKey);
    }

    /**
     * copying an object
     * @param sourceBucketName
     * @param sourceKey
     * @param destinationBucketName
     * @param destinationKey
     * @return
     */
    public CopyObjectResult copyObject(
            String sourceBucketName,
            String sourceKey,
            String destinationBucketName,
            String destinationKey
    ) {
        return s3client.copyObject(
                sourceBucketName,
                sourceKey,
                destinationBucketName,
                destinationKey
        );
    }

    /**
     * deleting an object
     * @param bucketName
     * @param objectKey
     */
    public void deleteObject(String bucketName, String objectKey) {
        s3client.deleteObject(bucketName, objectKey);
    }

    /**
     * deleting multiple Objects
     * @param delObjReq
     * @return
     */
    public DeleteObjectsResult deleteObjects(DeleteObjectsRequest delObjReq) {
        return s3client.deleteObjects(delObjReq);
    }
}
