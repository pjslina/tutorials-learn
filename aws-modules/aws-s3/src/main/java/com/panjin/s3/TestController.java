package com.panjin.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author panjin
 */
@RestController
public class TestController {

    private static final AWSCredentials credentials;
    private static String bucketName;
    private static final AwsClientBuilder.EndpointConfiguration endPoint;
    private static S3Service s3Service;

    static {
        //put your accesskey and secretkey here
        credentials = new BasicAWSCredentials(
                "lMyjSQXBY0q86K7t",
                "SCMJBaFwC1zIP9WhYtsM4TDmUxCUZgmE"
        );

        endPoint = new AwsClientBuilder.EndpointConfiguration(
                "http://192.168.16.101:9000", Regions.US_EAST_2.getName());

        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(endPoint)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

        s3Service = new S3Service(s3client);
        bucketName = "panjin-bucket";
    }

    @GetMapping("/get")
    public void getFile( HttpServletResponse response) {
        try {
            String id = "/1/需求 管理.xlsx";
            // 这里获取文件的名称
            String name = FilenameUtils.getName(id);
            // 下面这一行代码是解决空格字符会转换成“+”号的问题
            String fileName = new String(name.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
            s3Service.getFile(bucketName, id, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
