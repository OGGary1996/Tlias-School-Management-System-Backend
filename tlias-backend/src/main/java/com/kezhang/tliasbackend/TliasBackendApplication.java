package com.kezhang.tliasbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kezhang.tliasbackend.mapper")
public class TliasBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TliasBackendApplication.class, args);
	}

}
