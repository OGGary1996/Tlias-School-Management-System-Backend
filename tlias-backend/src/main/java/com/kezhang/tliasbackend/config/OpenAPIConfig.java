package com.kezhang.tliasbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 标记这个类为配置类，底层已经包含了@Component注解
public class OpenAPIConfig {
    /*
    * This is a configuration class for OpenAPI.
    * It defines a bean that provides an OpenAPI instance.
    * The OpenAPI instance can be used to customize the API documentation.
    * */
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info()
                .title("Tlias Backend API")
                .description("Includes all APIs for Tlias Backend,like department, user, etc.")
                .version("0.0.1"));
    }
}
