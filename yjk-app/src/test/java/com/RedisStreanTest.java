package com;

import java.util.ArrayList;
import java.util.List;

import edu.emory.mathcs.backport.java.util.Collections;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisStreanTest {

	public static void main(String[] args) {
		Jedis  jedis = new Jedis("39.106.4.135", 6379);
		

/*		String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
		Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
		if (RELEASE_SUCCESS.equals(result)) {
			return true;
		}*/
/*		String script = "redis.call('subscribe',KEYS[1])";
		List<String> arrayList = new ArrayList<>();
		Object result = jedis.eval(script, Collections.singletonList("redisChat"), arrayList);*/
		jedis.subscribe(new ps(), "redisChat");
		
	}
	
}

class ps extends JedisPubSub{

	@Override
	public void onMessage(String channel, String message) {
		System.out.println(channel +"--"+message);
		System.out.println("我监听到了  1");
		super.onMessage(channel, message);
	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		System.out.println("我监听到了  2");
		super.onSubscribe(channel, subscribedChannels);
	}

	@Override
	public void psubscribe(String... patterns) {
		System.out.println("我监听到了  3");
		super.psubscribe(patterns);
	}
	
}
