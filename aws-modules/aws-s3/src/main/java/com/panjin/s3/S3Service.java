package com.panjin.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author panjin
 */
public class S3Service {

    private final AmazonS3 s3client;
    private static final String slash = "/";

    public S3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public void createFile(String bucketName, String key, File file) {
        createFileWithDirPrefix(bucketName, key);
        s3client.putObject(bucketName, key, file);
    }

    private void createFileWithDirPrefix(String bucketName, String key) {
        if (StringUtils.isBlank(key) || key.endsWith(slash)) {
            throw new RuntimeException("文件名称不合法");
        }
        key = key.substring(0, key.lastIndexOf(slash));
        if (!"".equals(key)) {
            createDir(bucketName, key);
        }
    }

    public void createFile(String bucketName, String key, InputStream input) {
        createFileWithDirPrefix(bucketName, key);
        ObjectMetadata metadata = new ObjectMetadata();
        s3client.putObject(bucketName, key, input, metadata);
    }

    public void creatFile(String bucketName, String key, String content) {
        createFileWithDirPrefix(bucketName, key);
        s3client.putObject(bucketName, key, content);
    }

    public String getFile(String bucketName, String key) {
        String result = "";
        S3Object object = s3client.getObject(bucketName, key);
        S3ObjectInputStream objectContent = objectContent = object.getObjectContent();
        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream();
            getFile(bucketName, key, os);
            result = new String(os.toByteArray());
        } catch (Exception e) {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException ex) {
                    e.addSuppressed(ex);
                }
            }
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return result;
    }

    public void getFile(String bucketName, String key, OutputStream os) {
        S3Object object = s3client.getObject(bucketName, key);
        S3ObjectInputStream objectContent = objectContent = object.getObjectContent();
        try {
            IOUtils.copy(objectContent, os);
        } catch (Exception e) {
            if (objectContent != null) {
                try {
                    objectContent.close();
                } catch (IOException ex) {
                    e.addSuppressed(ex);
                }
            }
        } finally {
            if (objectContent != null) {
                try {
                    objectContent.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public boolean exist(String bucketName, String objectName) {
        return s3client.doesObjectExist(bucketName, objectName);
    }

    public void createDir(String bucketName, String path) {
        path = getPathWithValid(path);
        if (exist(bucketName, path)) {
            System.out.println("Directory already exists");
            return;
        }
        String[] itemDir = path.split(slash);

        for (int i = 0; i < itemDir.length; i++) {
            StringBuilder item = new StringBuilder("");
            for (int j = 0; j < i; j++) {
                item.append(itemDir[j] + slash);
            }
            item.append(itemDir[i] + slash);
            if (!exist(bucketName, item.toString())) {
                s3client.putObject(bucketName, item.toString(), "");
            }
        }
    }

    public void deleteDir(String bucketName, String path) {
        List<S3ObjectSummary> objectSummaries = getS3ObjectSummaries(path, bucketName);
        if (0 == objectSummaries.size()) {
            System.out.println("删除目录及子目录，但该目录不存在");
            return;
        }
        if (2 == objectSummaries.size()) {
            System.out.println("该目录下存在文件或者子目录");
            return;
        }
        s3client.deleteObject(bucketName, objectSummaries.get(0).getKey());
    }

    private List<S3ObjectSummary> getS3ObjectSummaries(String path, String bucketName) {
        path = getPathWithValid(path);
        ObjectListing dirObjs = s3client.listObjects(bucketName, path);
        List<S3ObjectSummary> objectSummaries = dirObjs.getObjectSummaries();
        return objectSummaries;
    }

    public void deleteDirOfAll(String bucketName, String path) {
        List<S3ObjectSummary> objectSummaries = getS3ObjectSummaries(path, bucketName);
        if (0 == objectSummaries.size()) {
            System.out.println("删除目录及子目录，但该目录不存在");
            return;
        }
        List<String> keys = new ArrayList<>();
        objectSummaries.forEach(f -> {
            keys.add(f.getKey());
            System.out.println("目录===" + f.getKey());
        });
        DeleteObjectsRequest objectsRequest = new DeleteObjectsRequest(bucketName)
                .withKeys(keys.toArray(new String[keys.size()]));
        s3client.deleteObjects(objectsRequest);
    }

    private String getPathWithValid(String path) {
        path = path.replaceAll("^" + slash, "");
        if (!path.endsWith(slash)) {
            path += slash;
        }
        return path;
    }

}
