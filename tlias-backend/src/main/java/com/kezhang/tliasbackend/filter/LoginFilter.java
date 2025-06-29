package com.kezhang.tliasbackend.filter;

import com.kezhang.tliasbackend.constant.ErrorCodeEnum;
import com.kezhang.tliasbackend.constant.FilterWhitelistEnum;
import com.kezhang.tliasbackend.exception.LoginRequiredException;
import com.kezhang.tliasbackend.exception.UnauthorizedUserException;
import com.kezhang.tliasbackend.utils.JWTUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component // 将这个过滤器注册为Spring Bean,才能使用@Autowired注入JWTUtil
// @WebFilter(urlPatterns = "/*") // 表示注册为一个过滤器，拦截所有请求
public class LoginFilter implements Filter {
    // 获取白名单中的URI
    private final static List<String> WHITELIST_URIS = FilterWhitelistEnum.getURIs();
    // 注入JWTUtil
    private final JWTUtil jwtUtil;
    private final HandlerExceptionResolver handlerExceptionResolver;
    @Autowired
    public LoginFilter(JWTUtil jwtUtil, HandlerExceptionResolver handlerExceptionResolver) {
        this.jwtUtil = jwtUtil;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    /*
    * 过滤控制流程：
    *  1. 首先判断请求是否属于白名单，如果是，则直接放行。
    *  2. 如果请求不属于白名单，则进行校验：
    *     1. 如果Authorization头不存在，则抛出未登录异常。
    *     2. 如果Authorization头存在，则获取token并验证其有效性：
    * */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // 获取请求的URI,注意：获取的是URI而不是URL
        // 区别：URL是协议+主机名+端口号+URI，而URI是请求的路径部分，不包含协议、主机名和端口号
        String requestURI = httpRequest.getRequestURI();

        // 判断请求URI是否在白名单中
        if (WHITELIST_URIS.contains(requestURI)) {
            log.info("Request URI {} is in whitelist, allowing access.", requestURI);
            chain.doFilter(request, response); // 如果在白名单中，则直接放行请求
            // 执行放行代码之后，接口的返回值还会回到放行的代码这里，所以需要使用return避免回来之后继续执行后续的代码
            return ;
        }
        // 如果不在白名单中，则需要进行登录验证
        log.info("Request URI {} is not in whitelist, checking login status.", requestURI);

        // 注意：如果前端根本没有设置Authorization头，则httpRequest.getHeader("Authorization")会返回null
        // 我们需要先判断是否Authorization头是否存在，如果不存在，则直接抛出未登录异常；不用先获取token
        String authorizationHeader = httpRequest.getHeader("Authorization");
        log.info("Authorization header: {}", authorizationHeader);
        // 判断是否为空
        if(authorizationHeader == null || authorizationHeader.isEmpty()){
            log.warn("Authorization header is missing or empty, throwing LoginRequiredException.");
            LoginRequiredException loginRequiredException = new LoginRequiredException(
                    ErrorCodeEnum.LOGIN_REQUIRED.getCode(),
                    ErrorCodeEnum.LOGIN_REQUIRED.getMessage());
            // 使用HandlerExceptionResolver把异常交给Spring处理
            handlerExceptionResolver.resolveException(httpRequest, httpResponse, null, loginRequiredException);
            return ; // 直接返回，不再执行后续代码
        }

        // 如果不为空，此时再获取token
        // token的标准写法是 "Bearer <token>"，所以需要去掉"Bearer "前缀
        String token = authorizationHeader.substring(7); // 去掉前缀"Bearer "
        log.info("Authorization header is present, validating token.");
        try {
            jwtUtil.parseToken(token);
        } catch (Exception e) { // 如果验证过程中抛出异常，则说明验证失败，再手动抛出未授权异常
            UnauthorizedUserException unauthorizedUserException = new UnauthorizedUserException(
                    ErrorCodeEnum.UNAUTHORIZED_USER.getCode(),
                    ErrorCodeEnum.UNAUTHORIZED_USER.getMessage());
            // 使用HandlerExceptionResolver把异常交给Spring处理
            handlerExceptionResolver.resolveException(httpRequest, httpResponse, null, unauthorizedUserException);
            return ; // 直接返回，不再执行后续代码
        }

        // 如果验证通过，则放行请求
        log.info("Token is valid, allowing access to the requested resource.");
        chain.doFilter(request, response); // 放行请求
    }
}
