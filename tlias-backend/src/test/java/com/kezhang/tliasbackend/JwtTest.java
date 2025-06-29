package com.kezhang.tliasbackend;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import java.util.Date;


public class JwtTest {
    // 设置测试类静态变量
    private static final String SECRET_CODE = "YWVmaHdlaFBPSUhIRkFXR3dlaGZvd2lAKiZeKl4qJSomVCo"; // "aefhwehPOIHHFAWGwehfowi@*&^*^*%*&T*" -> Base64 encoded

    @Test
    public void testGenerateToken() {
        String token = Jwts.builder()
                // 设置payload
                .setSubject("testUser") // 设置主题sub
                .claim("userId", 123) // 设置自定义字段
                .claim("role", "admin") // 设置自定义字段
                .claim("username", "testUser") // 设置自定义字段
                .setIssuedAt(new Date()) // new Date() 获取当前时间
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000L)) // 设置过期时间为7天
                // 设置签名算法和密钥
                .signWith(Keys.hmacShaKeyFor(SECRET_CODE.getBytes()),SignatureAlgorithm.HS256) // 使用HS256算法和密钥
                .compact();// 生成JWT字符串
        System.out.println("Token generated successfully: " + token);
    }

    @Test
    public void testParseToken(){
        // 假设这是之前生成的Token
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0VXNlciIsInVzZXJJZCI6MTIzLCJyb2xlIjoiYWRtaW4iLCJ1c2VybmFtZSI6InRlc3RVc2VyIiwiaWF0IjoxNzUxMTMxOTg4LCJleHAiOjE3NTE3MzY3ODh9.GV_3ExA-8O9-ElCbYBFvDoZDsIaxphHtvHLE0pb8Z_o";

        // 1. 创建Parser对象
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_CODE.getBytes()))
                .build();
        // 2. 解析Token
        Claims claims = parser.parseClaimsJws(token)
                .getBody();
        // 3. 获取Claims中的信息
        String subject = claims.getSubject();
        Integer userId = claims.get("userId", Integer.class);
        String role = claims.get("role", String.class);
        String username = claims.get("username", String.class);
        // 4. 打印解析结果
        System.out.println("Token parsed successfully:");
        System.out.println("Subject: " + subject);
        System.out.println("User ID: " + userId);
        System.out.println("Role: " + role);
        System.out.println("Username: " + username);
    }
}
