package com.kezhang.tliasbackend.config;

import com.kezhang.tliasbackend.constant.FilterWhitelistEnum;
import com.kezhang.tliasbackend.proxy.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration // 标记这个类为配置类，底层已经包含了@Component注解
public class WebConfig implements WebMvcConfigurer {
    // 这里可以添加其他的Web配置，例如拦截器、视图解析器等
    // 目前只有拦截器配置在InterceptorConfig类中

    // 获取白名单中的URI
    private static final List<String> WHITELIST_URIS = FilterWhitelistEnum.getURIs();
    // 注入LoginInterceptor
    private final LoginInterceptor loginInterceptor;
    @Autowired
    public WebConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    // 添加拦截器方法
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**") // 拦截所有请求
                .excludePathPatterns(WHITELIST_URIS); // 排除白名单中的URI
    }
}
