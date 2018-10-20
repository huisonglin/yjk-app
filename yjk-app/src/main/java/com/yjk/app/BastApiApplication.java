package com.yjk.app;

import java.math.BigDecimal;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
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
