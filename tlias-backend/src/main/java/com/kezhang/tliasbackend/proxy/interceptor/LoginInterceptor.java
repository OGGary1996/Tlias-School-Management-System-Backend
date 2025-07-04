package com.kezhang.tliasbackend.proxy.interceptor;

import com.kezhang.tliasbackend.common.context.LoginContext;
import com.kezhang.tliasbackend.constant.ErrorCodeEnum;
import com.kezhang.tliasbackend.exception.UnauthorizedUserException;
import com.kezhang.tliasbackend.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    // 参数注入
    private final JWTUtil jwtUtil;
    @Autowired
    public LoginInterceptor(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // 拦截器方法
    /*
    * 拦截流程：
    *  1. 白名单的判断已经在注册Interceptor时指定了
    *  2. 首先判断Authorization头是否存在，如果不存在，则直接抛出未登录异常
    *  3. 如果Authorization头存在，则获取token并验证其有效性：
    *     1. 如果token验证成功，则放行请求，并且设置ThreadLocal中的用户信息
    *     2. 如果token验证失败，则抛出未授权异常
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("LoginInterceptor: preHandle method called.");
        // 获取Authorization头
        String authorizationHeader = request.getHeader("Authorization");
        // 进行判空，如果为空，则直接抛出未登录异常
        if (authorizationHeader == null || authorizationHeader.isEmpty()){
            log.error("Authorization header is missing, throwing LoginRequiredException.");
            throw new UnauthorizedUserException(
                    ErrorCodeEnum.UNAUTHORIZED_USER.getCode(),
                    ErrorCodeEnum.UNAUTHORIZED_USER.getMessage());
            // 注意：这里不需要使用HandlerExceptionResolver来处理异常，因为Interceptor会把异常交给全局异常处理器来处理
            // 也不需要返回false，因为抛出异常后，Interceptor会自动中断请求的处理流程
        }
        // 如果Authorization头存在，则获取token并验证其有效性
        String token = authorizationHeader.substring(7); // 截取Bearer 后面的部分
        Claims claims = null;
        log.info("Authorization header found, token: {}", token);
        try{
            claims = jwtUtil.parseToken(token);
        } catch (Exception e) {
            log.error("Token validation failed: {}", e.getMessage());
            throw new UnauthorizedUserException(
                    ErrorCodeEnum.UNAUTHORIZED_USER.getCode(),
                    ErrorCodeEnum.UNAUTHORIZED_USER.getMessage());
            // 注意：这里不需要使用HandlerExceptionResolver来处理异常，因为Interceptor会把异常交给全局异常处理器来处理
            // 也不需要返回false，因为抛出异常后，Interceptor会自动中断请求的处理流程
        }
        // 如果以上步骤都通过了，则放行请求，并且设置ThreadLocal中的用户信息
        log.info("Token validation successful, allowing access.");
        LoginContext.setId(claims.get("id",Integer.class));
        LoginContext.setName(claims.get("name",String.class));
        return true; // 这里的return true 表示放行请求
    }

    /*
    * 该方法在请求处理完成后执行
    * 用于清理ThreadLocal中的用户信息
    * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("LoginInterceptor: afterCompletion method called, clearing ThreadLocal.");
        // 清理ThreadLocal中的用户信息
        LoginContext.clearId();
        LoginContext.clearName();
        // 注意：这里不需要返回值，因为afterCompletion方法没有返回值
        // 如果有异常，则会被全局异常处理器处理
    }
}
