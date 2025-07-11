package com.kezhang.tliasbackend.config;

import com.kezhang.tliasbackend.utils.LocalDriveUtil;
import com.kezhang.tliasbackend.utils.LocalDriveUtilProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LocalDriveUtilProperties.class)
public class LocalDriveConfig {
    @Bean
    @ConditionalOnMissingBean
    public LocalDriveUtil localDriveUtil(LocalDriveUtilProperties localDriveUtilProperties) {
        return new LocalDriveUtil(localDriveUtilProperties);
    }
}
