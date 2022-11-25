package com.panjin.s3;

public class S3Test {

    private static final String slash = "/";

    public static void main(String[] args) {
        String path = "/a";
        a(path);
        System.out.println("after="+path);
    }

    public static void a(String path) {
        if (path.endsWith(slash)) {
            throw new RuntimeException("文件名称不合法");
        }
        path = path.substring(0, path.lastIndexOf(slash));
        System.out.println("path=" + path);
        if ("".equals(path)) {
            return;
        } else {
            System.out.println(path);
        }
    }
}
