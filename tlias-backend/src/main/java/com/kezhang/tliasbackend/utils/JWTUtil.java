package com.kezhang.tliasbackend.utils;

import com.kezhang.tliasbackend.dto.EmployeeLoginResponseDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JWTUtil {
    private final JWTUtilProperties jwtUtilProperties;
    @Autowired
    public JWTUtil(JWTUtilProperties jwtUtilProperties) {
        this.jwtUtilProperties = jwtUtilProperties;
    }


    /*
    * 生成JWT Token的流程：
    *  1. 接收EmployeeLoginResponseDTO对象，获取员工信息，以供插入到JWT的payload中
    *  2. 使用JWTUtilProperties中的参数
    *  3. 使用Jwts.builder()方法构建Jwts对象
    *     1. 设置payload中的信息，包括：sub、id、name、过期时间 等
    *     2. 使用Keys.hmacShaKeyFor()方法生成签名密钥
    *     3. 使用SignatureAlgorithm.forName()方法设置签名算法，
    *        注意：使用SignatureAlgorithm中的各种加密算法为枚举类，但是由于我们打算在配置文件中指定加密算法，所以这里使用forName()方法来动态获取加密算法String
    *  4. 最后调用Jwts对象中的compact()方法生成JWT Token
    * */
    public String generateToken(EmployeeLoginResponseDTO employeeLoginResponseDTO) {
        // 1. 获取EmployeeLoginResponseDTO中的员工信息
        Integer employeeId = employeeLoginResponseDTO.getId();
        String username = employeeLoginResponseDTO.getUsername();
        String name = employeeLoginResponseDTO.getName();
        log.info("Generating JWT token for employee: ID={}, Username={}, Name={}", employeeId, username, name);

        // 2. 使用JWTUtilProperties中的secretCode和algorithm生成token
        String token = Jwts.builder()
                // 设置payload
                .setSubject(username) // 设置主题为用户名
                .claim("id", employeeId) // 设置自定义字段id
                .claim("name", name) // 设置自定义字段name
                .setIssuedAt(new Date()) // 设置签发时间为当前时间
                .setExpiration(new Date(System.currentTimeMillis() + jwtUtilProperties.getExpiration())) // 设置过期时间
                // 设置签名算法和密钥
                .signWith(Keys.hmacShaKeyFor(jwtUtilProperties.getSecretCode().getBytes()),
                        SignatureAlgorithm.forName(jwtUtilProperties.getAlgorithm()))
                .compact();
        log.info("Generated JWT token: {}", token);
        return token;
    }

    /*
    * 解析JWT Token的流程：
    *  1. 接收JWT Token字符串
    *  2. 使用Jwts.parserBuilder()方法创建解析器Parser对象
    *     1. 指定生成token时的密钥
    *     2. build()方法创建解析器
    *  3. 使用Parser对象的parseClaimsJws()方法解析JWT Token字符串
    *  4. 使用getBody()方法获取payload中的信息，也就是Claims对象
    * 注意：
    *    如果获取Claims的过程中发生异常，则说明解析失败了
    *    这个异常不能交给全局异常处理器处理，需要在Filter中捕获并且抛出具体的业务异常
    *    如果直接抛出Exception，则会被全局异常处理器捕获，导致无法返回具体的错误信息
    * */
    public Claims parseToken(String token) throws Exception {
        Claims claims = Jwts.parserBuilder() // 构建parser对象
                .setSigningKey(Keys.hmacShaKeyFor(jwtUtilProperties.getSecretCode().getBytes())) // 设置签名密钥
                .build() // 创建解析器
                .parseClaimsJws(token) // 使用Parser对象的parseClaimsJws()方法解析JWT Token字符串
                .getBody(); // 获取payload中的信息，也就是Claims对象
        log.info("Parsed JWT token claims: {}", claims);
        return claims;
    }
}
