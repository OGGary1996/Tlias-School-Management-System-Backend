package com.kezhang.tliasbackend.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTUtilProperties {
    private String secretCode;
    private String algorithm; // 签名算法
    private Long expiration; // in milliseconds
}
