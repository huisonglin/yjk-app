package com.yjk;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

import tk.mybatis.spring.annotation.MapperScan;


@EnableAspectJAutoProxy
@EnableCaching
@EnableAsync
@SpringBootApplication
@MapperScan("com.yjk.common.dao")
public class BastApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(BastApiApplication.class, args);
	}
}
