package com.kezhang.tliasbackend.config;

import com.kezhang.tliasbackend.utils.JWTUtil;
import com.kezhang.tliasbackend.utils.JWTUtilProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JWTUtilProperties.class) // 注册JWTUtilProperties参数映射类
public class JWTConfig {
    @Bean
    @ConditionalOnMissingBean
    public JWTUtil jwtUtil(JWTUtilProperties jwtUtilProperties){
        return new JWTUtil(jwtUtilProperties);
    }
}
