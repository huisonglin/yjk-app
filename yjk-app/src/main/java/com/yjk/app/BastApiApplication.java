package com.yjk.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import tk.mybatis.spring.annotation.MapperScan;


@EnableAspectJAutoProxy
@EnableCaching
@SpringBootApplication
@MapperScan("com.yjk.app.dao")
public class BastApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(BastApiApplication.class, args);
	}

}
