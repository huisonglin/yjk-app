package com.yjk.app.redis.subscribe;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.yjk.app.redis.lock.config.RedisPool;

import redis.clients.jedis.Jedis;

/*@Component*/
public class RedisLister {

/*	Jedis jedis = RedisPool.getJedis();
	
	@Autowired
	RedisSubscribe redisSubscribe;
	
	@Async
	public void listerMessage() {	
		jedis.subscribe(redisSubscribe, "redisChat");
	}*/
}
