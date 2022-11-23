package com.panjin.s3;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;

import java.io.File;
import java.util.concurrent.Executors;

/**
 * @author panjin
 */
public class MultipartUpload {

    public static void main(String[] args) {
        String existingBucketName = "panjin-bucket";
        String keyName = "my-picture.jpg";
        String filePath = "C:\\Users\\panjin\\Downloads\\my-picture.jpg";

        AWSCredentials credentials = new BasicAWSCredentials(
                "lMyjSQXBY0q86K7t",
                "SCMJBaFwC1zIP9WhYtsM4TDmUxCUZgmE"
        );

        AwsClientBuilder.EndpointConfiguration endPoint = new AwsClientBuilder.EndpointConfiguration(
                "http://192.168.16.101:9000", Regions.US_EAST_2.getName());

        AmazonS3 amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(endPoint)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

        int maxUploadThreads = 5;

        TransferManager tm = TransferManagerBuilder
                .standard()
                .withS3Client(amazonS3)
                .withMultipartUploadThreshold((long) (5 * 1024 * 1024))
                .withExecutorFactory(() -> Executors.newFixedThreadPool(maxUploadThreads))
                .build();

        ProgressListener progressListener =
                progressEvent -> System.out.println("Transferred bytes: " + progressEvent.getBytesTransferred());

        PutObjectRequest request = new PutObjectRequest(existingBucketName, keyName, new File(filePath));

        request.setGeneralProgressListener(progressListener);

        Upload upload = tm.upload(request);

        try {
            upload.waitForCompletion();
            System.out.println("Upload complete.");
        } catch (AmazonClientException | InterruptedException e) {
            System.out.println("Error occurred while uploading file");
            e.printStackTrace();
        }
    }
}
