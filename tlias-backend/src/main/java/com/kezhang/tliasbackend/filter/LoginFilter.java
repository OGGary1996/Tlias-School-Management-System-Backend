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

import java.io.IOException;
import java.util.List;

@Slf4j
@Component // 将这个过滤器注册为Spring Bean,才能使用@Autowired注入JWTUtil
@WebFilter(urlPatterns = "/*") // 表示这个过滤器会拦截所有的请求
public class LoginFilter implements Filter {
    // 获取白名单中的URI
    private final static List<String> WHITELIST_URIS = FilterWhitelistEnum.getURIs();
    // 注入JWTUtil
    private final JWTUtil jwtUtil;
    @Autowired
    public LoginFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /*
    * 过滤控制流程：
    *  1. 首先判断请求是否属于白名单，如果是，则直接放行。
    *  2. 如果请求不属于白名单，则进行校验：
    *     1. 获取请求头中的token
    *     2. 如果token不存在，则返回未登录错误。
    *     3. 如果token存在，则验证token的有效性：如果有效，则放行；如果无效，则返回未授权错误。
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
            return ;
        }
        // 如果不在白名单中，则进行登录验证
        log.info("Request URI {} is not in whitelist, checking login status.", requestURI);

        // 获取请求头中的token,token包含在前端请求头的Authorization字段中
        // 标准写法是 "Bearer <token>"
        String token = httpRequest.getHeader("Authorization").substring(7); // 去掉前面的"Bearer "部分
        log.info("Authorization header: {}", token);
        // 判断是否为空
        if(token == null || token.isEmpty()){
            log.warn("Authorization header is missing or empty, throwing LoginRequiredException.");
            throw new LoginRequiredException(
                    ErrorCodeEnum.LOGIN_REQUIRED.getCode(),
                    ErrorCodeEnum.LOGIN_REQUIRED.getMessage());
        }
        // 如果不为空，则验证token的有效性
        log.info("Authorization header is present, validating token.");
        try {
            jwtUtil.parseToken(token);
        } catch (Exception e) { // 如果验证过程中抛出异常，则说明验证失败，再手动抛出未授权异常
            throw new UnauthorizedUserException(
                    ErrorCodeEnum.UNAUTHORIZED_USER.getCode(),
                    ErrorCodeEnum.UNAUTHORIZED_USER.getMessage());
        }

        // 如果验证通过，则放行请求
        log.info("Token is valid, allowing access to the requested resource.");
        chain.doFilter(request, response); // 放行请求
    }
}
