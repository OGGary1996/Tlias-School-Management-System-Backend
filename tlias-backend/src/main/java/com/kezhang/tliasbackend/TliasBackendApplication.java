package com.kezhang.tliasbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.kezhang.tliasbackend.mapper") // Scan for MyBatis mappers in the specified package，需要明确Resources目录下的Mapper文件夹,与src/main/java目录下的Mapper包对应
@ServletComponentScan // 扫描Servlet组件，主要用于扫描自定义的Filter
public class TliasBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TliasBackendApplication.class, args);
	}

}
