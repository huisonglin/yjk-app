package com;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.yjk.app.redis.lock.RedisLock;

import redis.clients.jedis.Jedis;

@Service
public class IndexService {
	public void sayHello() {
		System.out.println("Hello world!");
	}

	public static void main(String[] args) {
		try {
			CountDownLatch start = new CountDownLatch(1);// 初始化计数器为 一
			RedisLock redisLook = new RedisLock("lock",1000);
			for (int i = 0; i < 12; i++) {// 模擬16个线程
				MyTestThread tt = new MyTestThread(start, redisLook);
				Thread t = new Thread(tt);
				t.start();
			}
			start.countDown();// 计数器減一 所有线程释放 同时跑
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

		}
		System.out.println(MyTestThread.age);
	}

}
