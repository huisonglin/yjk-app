package com;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjk.app.interceptor.AuthorizationInterceptor;
import com.yjk.app.util.JwtUtils;

import io.jsonwebtoken.Claims;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BastApiApplicationTests {

/*	@Autowired
	ReadMapper readMapper;
	@Test
	public void contextLoads() {
		PageHelper.startPage(2, 1);
		List<User> selectAll = readMapper.selectAll();
		PageInfo<User> userPageInfo = new PageInfo<>(selectAll);
		System.out.println(JSON.toJSONString(userPageInfo));
		List<User> list = userPageInfo.getList();
		for (User user : list) {
			System.out.println(user);
		}
	}*/
	@Autowired
	JwtUtils jwtUtils;
	
	//eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMDAxIiwiaWF0IjoxNTMzNDI5MTcxLCJleHAiOjE1MzU0MjkxNzF9.-y4m59gvYdWul0XPpBYMf0cQqO0U_nRToaaVvFeYwTo8ZKTLtZYKAIBXpuMnx3IMcIBOFMy8jM-dYYivYywxrQ
	//{"sub":"0001","iat":1533429171,"exp":1535429171}
/*	@Test
	public void testJwtUtils() throws Exception {
		String generateToken = jwtUtils.generateToken("0001");
		System.out.println(generateToken);
		Claims claimByToken = jwtUtils.getClaimByToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMDAxIiwiaWF0IjoxNTMzNDI5MTcxLCJleHAiOjE1MzU0MjkxNzF9.-y4m59gvYdWul0XPpBYMf0cQqO0U_nRToaaVvFeYwTo8ZKTLtZYKAIBXpuMnx3IMcIBOFMy8jM-dYYivYywxrQ");
		System.out.println(JSON.toJSONString(claimByToken));
		
		System.out.println(DateUtil.formatAsDatetime(claimByToken.getIssuedAt()));
		System.out.println(DateUtil.formatAsDatetime(claimByToken.getExpiration()));
	}*/
	@Autowired
	RedisTemplate redisTemplate;
	@Test
	public void testRedis() {
 /*       
        StringBuilder builder = new StringBuilder("limet_");
        builder.append("loc");
        builder.append("_");
        builder.append("ggg");
        ValueOperations opsForValue = redisTemplate.opsForValue();
        Long value = (Long)opsForValue.get(builder.toString());
        if(value == null) {  
        	Date date = new Date();
        	Long EachInterva =(long) (50*1000);
        	opsForValue.set(builder.toString(),date.getTime()+EachInterva,50,TimeUnit.SECONDS);  
        	System.out.println("chenggogn");
        }else {
        	Date date = new Date();
        	Integer a =(int) ((value-date.getTime())/1000);
        	System.out.println(a+"秒");
        }*/
		Long expire = redisTemplate.getExpire("limet_certication_192.168.137.1_Count", TimeUnit.SECONDS);
		System.out.println(expire);
	}
	

}
