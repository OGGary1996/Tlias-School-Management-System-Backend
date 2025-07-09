package com.kezhang.aliyunossoperator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 声明配置类
@EnableConfigurationProperties(AliyunOssProperties.class) // 注册配置属性类
public class AliyunOssConfig {

    @Bean // 注册 AliyunOssUtil Bean
    @ConditionalOnMissingBean
    public AliyunOssUtil aliyunOssUtil(AliyunOssProperties ossProperties) {
        return new AliyunOssUtil(ossProperties);
    }
}
