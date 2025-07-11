package com.kezhang.tliasbackend.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "uploads")
public class LocalDriveUtilProperties {
    private String path; // 本地存储路径
    private String urlPrefix; // 文件访问的URL前缀
}
