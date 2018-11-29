package com.yjk.app.test;

import java.util.List;

import redis.clients.jedis.Jedis;

public class Goodz {
	
	public static Jedis jedis;
	static {
		jedis = new Jedis("YunJiKuRedis.redis.cache.chinacloudapi.cn",6379);
		jedis.auth("/jq+6e4CyqOca8BSAZhv6XqcORRFLDSkqAVhc7V/dt4=");
	}

	public static void main(String[] args) {
		Goodz g = new Goodz();
		while(true) {
			g.dealRequest();
		}
	}

	public void dealRequest() {
		List<String> blpop = jedis.blpop(10, "xtkg");
		for (String string : blpop) {
			System.out.println(string);
		}
	}
	
}
