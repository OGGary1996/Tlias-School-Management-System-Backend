package com.kezhang.tliasbackend.controller;

import com.kezhang.tliasbackend.common.Result;
import com.kezhang.tliasbackend.dto.EmployeeLoginInfoDTO;
import com.kezhang.tliasbackend.dto.EmployeeLoginResponseDTO;
import com.kezhang.tliasbackend.service.LoginService;
import com.kezhang.tliasbackend.utils.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
@Tag(name = "LoginController", description = "Handle user login operations")
public class LoginController {
    private final LoginService loginService;
    private final JWTUtil jwtUtil;
    public LoginController(LoginService loginService, JWTUtil jwtUtil) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

//    /*
//    * 处理用户登录请求，本例子采用手动设置Cookie的方式来处理登录验证
//    * @param employeeLoginInfoDTO 包含用户名和密码的登录信息
//    * @return 返回登录响应，包含用户信息和 token
//    * */
//    @Operation(summary = "User login", description = "Handles user login operations")
//    @PostMapping
//    public Result<?> login(@RequestBody EmployeeLoginInfoDTO employeeLoginInfoDTO, HttpServletResponse response) {
//        log.info("Login request received for user: {}", employeeLoginInfoDTO.getUsername());
//        // 调用 EmployeeService 的登录方法
//        EmployeeLoginResponseDTO employeeLoginResponseDTO = loginService.login(employeeLoginInfoDTO);
//        log.info("Login successful for user: {}", employeeLoginInfoDTO.getUsername());
//
//        // 手动设置Cookie
//        // 1. 设置 Cookie 的Name="login_user",Value=employeeLoginResponseDTO.getUsername()
//        Cookie loginCookie = new Cookie("login_user",employeeLoginResponseDTO.getUsername());
//        // 2. 设置MaxAge为7天,有效期为7天
//        loginCookie.setMaxAge(7 * 24 * 60 * 60); // 7 days in seconds
//        // 3. 设置Path为"/",表示Cookie在整个应用中所有的路径都有效
//        loginCookie.setPath("/");
//        // 4. 设置HttpOnly为true,表示Cookie只能通过HTTP请求访问，JavaScript无法访问
//        loginCookie.setHttpOnly(true);
//        // 5. 添加Cookie到响应中,后续的请求都会携带这个Cookie
//        response.addCookie(loginCookie);
//
//        // 返回登录响应
//        return Result.success(employeeLoginResponseDTO);
//    }

//    /*
//    * 处理用户登录请求，本例子采用SessionID + Cookie的方式来处理登录验证
//    * @param employeeLoginInfoDTO 包含用户名和密码的登录信息
//    * @return 返回登录响应，包含用户信息和 token
//    * */
//    @Operation(summary = "User login", description = "Handles user login operations")
//    @PostMapping
//    public Result<?> login(@RequestBody EmployeeLoginInfoDTO employeeLoginInfoDTO, HttpServletRequest httpServletRequest) {
//        log.info("Login request received for user: {}", employeeLoginInfoDTO.getUsername());
//        // 调用 EmployeeService 的登录方法
//        EmployeeLoginResponseDTO employeeLoginResponseDTO = loginService.login(employeeLoginInfoDTO);
//        log.info("Login successful for user: {}", employeeLoginInfoDTO.getUsername());
//
//        // 设置Session
//        // 1. 获取当前请求的Session，如果不存在则创建一个新的Session
//        HttpSession loginSession = httpServletRequest.getSession();
//        // 2. 将登录用户信息存储到Session中
//        loginSession.setAttribute("login_user", employeeLoginResponseDTO.getUsername());
//        // 3. 设置Session的最大失效时间为7天
//        loginSession.setMaxInactiveInterval(7 * 24 * 60 * 60); // 7 days in seconds
//        // 4. 设置路径以及其他属性
//        loginSession.setAttribute("path", "/");
//        loginSession.setAttribute("httpOnly", true);
//        loginSession.setAttribute("secure", false); // 如果需要HTTPS安全传输，可以设置为true
//        // 注意：Spring Boot默认会在浏览器端设置一个包含了JSESSIONID的Cookie
//
//        // 返回登录响应
//        return Result.success(employeeLoginResponseDTO);
//    }

    /*
    * 处理用户登录请求，本例子采用Token的方式来处理登录验证
    * @param employeeLoginInfoDTO 包含用户名和密码的登录信息
    * @return 返回登录响应，包含用户信息和 token
    * */
    @Operation(summary = "User login", description = "Handles user login operations")
    @PostMapping
    public Result<?> login(@RequestBody EmployeeLoginInfoDTO employeeLoginInfoDTO, HttpServletRequest httpServletRequest) {
        log.info("Login request received for user: {}", employeeLoginInfoDTO.getUsername());
        // 调用 EmployeeService 的登录方法
        EmployeeLoginResponseDTO employeeLoginResponseDTO = loginService.login(employeeLoginInfoDTO);
        log.info("Login successful for user: {}", employeeLoginInfoDTO.getUsername());

        // 设置Token
        String token = jwtUtil.generateToken(employeeLoginResponseDTO);
        log.info("Generated JWT token: {}", token);
        // 将Token设置到employeeLoginResponseDTO中
        employeeLoginResponseDTO.setToken(token);
        log.info("Token set in login response for user: {}", employeeLoginResponseDTO);

        // 返回登录响应
        return Result.success(employeeLoginResponseDTO);
    }
}
