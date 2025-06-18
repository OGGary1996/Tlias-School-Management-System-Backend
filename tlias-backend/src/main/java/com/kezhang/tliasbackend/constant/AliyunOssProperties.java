package com.kezhang.tliasbackend.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="aliyun.oss")
public class AliyunOssProperties {
    private String bucketName;
    private String endpoint;
    private String region;
    private String dir;
}
