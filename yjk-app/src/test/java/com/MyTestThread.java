package com;
import java.util.concurrent.CountDownLatch;

import com.yjk.app.redis.lock.RedisLock;
public class MyTestThread implements Runnable {

	RedisLock redisLock = null;
	private final CountDownLatch startSignal;
	public static Integer age = 10;

	public MyTestThread(CountDownLatch startSignal, RedisLock redisLock) {
		super();
		this.redisLock = redisLock;
		this.startSignal = startSignal;
	}

	@Override
	public void run() {

		try {
			startSignal.await();

		} catch (Exception e) {
			// TODO Auto-generated catch block
		} // 一直阻塞当前线程，直到计时器的值为0

		doWork();

	}

	public void doWork() {
		redisLock.lock();
		Integer b = age;
		if (b > 0) {
			System.out.println(Thread.currentThread().getName());
			age = age - 1;
		}
		redisLock.unlock();

	}

}