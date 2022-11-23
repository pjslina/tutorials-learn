package com.panjin.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author panjin
 */
public class S3Application {

    private static final AWSCredentials credentials;
    private static String bucketName;
    private static final AwsClientBuilder.EndpointConfiguration endPoint;

    static {
        //put your accesskey and secretkey here
        credentials = new BasicAWSCredentials(
                "lMyjSQXBY0q86K7t",
                "SCMJBaFwC1zIP9WhYtsM4TDmUxCUZgmE"
        );

        endPoint = new AwsClientBuilder.EndpointConfiguration(
                "http://192.168.16.101:9000", Regions.US_EAST_2.getName());
    }

    public static void main(String[] args) throws IOException {

        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(endPoint)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

        AWSS3Service awsService = new AWSS3Service(s3client);

        bucketName = "panjin-bucket";

        if (awsService.doesBucketExist(bucketName)) {
            System.out.println("Bucket name is not available."
                    + " Try again with a different Bucket name.");
//            return;
        } else {
            awsService.createBucket(bucketName);
        }

        // list all the buckets
        for(Bucket s : awsService.listBuckets() ) {
            System.out.println(s.getName());
        }

        // deleting bucket
//        awsService.deleteBucket("panjin-bucket-test2");

        // uploading object
        awsService.putObject(
                bucketName,
                "Document/1.txt",
                new File("C:\\Users\\panjin\\Downloads\\1.txt")
        );

        //listing objects
        ObjectListing objectListing = awsService.listObjects(bucketName);
        for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
            System.out.println(os.getKey());
        }

        //downloading an object
        S3Object s3object = awsService.getObject(bucketName, "Document/1.txt");
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        FileUtils.copyInputStreamToFile(inputStream, new File("C:\\Users\\panjin\\Downloads\\hello.txt"));

        //copying an object
        awsService.copyObject(
                "panjin-bucket",
                "Document/1.txt",
                "panjin-bucket2",
                "Document/2.txt"
        );

        //deleting an object
        awsService.deleteObject(bucketName, "Document/1.txt");

        //deleting multiple objects
        String objkeyArr[] = {
                "Document/2.txt",
                "Document/picture.png"
        };

        DeleteObjectsRequest delObjReq = new DeleteObjectsRequest(bucketName)
                .withKeys(objkeyArr);
        awsService.deleteObjects(delObjReq);
    }
}
