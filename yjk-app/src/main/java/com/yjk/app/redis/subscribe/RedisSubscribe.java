package com.yjk.app.redis.subscribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yjk.app.dao.MemberMapper;
import com.yjk.app.entity.MemberDO;
import com.yjk.app.service.MemberService;

import redis.clients.jedis.JedisPubSub;

/*@Component*/
public class RedisSubscribe extends JedisPubSub{

/*	@Autowired
	MemberService memberService;
	
	@Override
	public void onMessage(String channel, String message) {
		System.out.println(channel +"--"+message);
		System.out.println("我监听到了  ");
		MemberDO m =new MemberDO();
		m.setNickName(message);
		memberService.selectByPrimaryKey(6L);
	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		System.out.println("\n\n\n\n已经开始订阅了\n\n\n");
		super.onSubscribe(channel, subscribedChannels);
	}*/
}
