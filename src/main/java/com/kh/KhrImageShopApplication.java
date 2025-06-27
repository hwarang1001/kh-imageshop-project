package com.kh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.kh.mapper")
public class KhrImageShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(KhrImageShopApplication.class, args);
	}

}
