package com.kezhang.aliyunossoperator;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="aliyun.oss")
public class AliyunOssProperties {
    private String bucketName;
    private String endpoint;
    private String region;
    private String dir;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        return "AliyunOssProperties{" +
                "bucketName='" + bucketName + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", region='" + region + '\'' +
                ", dir='" + dir + '\'' +
                '}';
    }
}
