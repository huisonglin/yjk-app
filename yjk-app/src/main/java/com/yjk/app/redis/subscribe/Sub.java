package com.yjk.app.redis.subscribe;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sub {

	@Autowired
	RedisLister redisLister;
	
	@PostConstruct
	public void startList() {
		redisLister.listerMessage();
	}
}
