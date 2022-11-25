package com.panjin.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author panjin
 */
public class S3DeleteApplication {

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
        S3Service s3Service = new S3Service(s3client);
        bucketName = "panjin-bucket";

        dirAndFileOperate(s3Service);

//        deleteOperate(awsService);
//        getPathWithValid();
    }

    private static void dirAndFileOperate(S3Service s3Service) {
//        s3Service.createDir(bucketName, "/a/b/c");
//        s3Service.deleteDir(bucketName, "/a/b/");
//        s3Service.deleteDirOfAll(bucketName, "/a/b");

//        s3Service.createFile(bucketName, "/1/需求 管理.xlsx", new File("C:\\Users\\panjin\\Downloads\\需求 管理.xlsx"));
        String file = s3Service.getFile(bucketName, "/1/需求 管理.xlsx");
    }

    private static void deleteOperate(AWSS3Service awsService) {

        // uploading object
        awsService.putObject(
                bucketName,
                "/a/b/c/d/1.txt",
                new File("C:\\Users\\panjin\\Downloads\\1.txt")
        );
        awsService.putObject(bucketName, "/a/b/c/", "");
        awsService.putObject(bucketName, "/a/b/c/d/", "");
        String prefix = "a/b/";
        ObjectListing objects = awsService.listObjects(bucketName, prefix);
        List<S3ObjectSummary> objectSummaries = objects.getObjectSummaries();
        List<String> keys = new ArrayList<>();
        objectSummaries.forEach(f -> {
            System.out.println(f.getKey());
            keys.add(f.getKey());
        });
        String[] deleteKeys = keys.toArray(new String[keys.size()]);
        DeleteObjectsRequest objectsRequest = new DeleteObjectsRequest(bucketName).withKeys(deleteKeys);
        awsService.deleteObjects(objectsRequest);
        ObjectListing afterDeleteObjects = awsService.listObjects(bucketName, prefix);
        System.out.println("删除之后的keys数量=" + afterDeleteObjects.getObjectSummaries().size());
    }

    private static void getPathWithValid() {
        String path = "//abc/de/f";
        System.out.println(path.replaceAll("^/", ""));
    }
}
