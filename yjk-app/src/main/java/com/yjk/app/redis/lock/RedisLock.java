package com.yjk.app.redis.lock;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import com.yjk.app.redis.lock.config.RedisPool;
import com.yjk.app.redis.lock.util.RedisUtil;

import redis.clients.jedis.Jedis;

public class RedisLock implements Lock {

	private ThreadLocal<String> local = new ThreadLocal<>();
	
	private String lockName;
	
	private Integer lockTime;
	public RedisLock(String lockName,Integer lockTime) {
		this.lockName = lockName;
		this.lockTime = lockTime;
	}
	public RedisLock() {
		super();
	}

	@Override
	public void lock() {
		if (tryLock()) {
			return;
		} else {
			try {
				Thread.sleep(new Random().nextInt(200));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lock();
		}

	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub

	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		String uuid = UUID.randomUUID().toString();
		Jedis jedis = RedisPool.getJedis();
		boolean setReisLock = false;
		try {
			setReisLock = RedisUtil.tryGetDistributedLock(jedis, lockName, uuid, lockTime);
		} catch (Exception e) {
			
		}finally {
			jedis.close();
		}
		if (setReisLock) {
			System.out.println(Thread.currentThread().getName()+"--"+uuid);
			local.set(uuid);
			System.out.println("加锁成功");
			return true;
		} else {
			System.out.println("尝试加锁失败");
		}
		return false;
	}

	@Override
	public boolean tryLock(long arg0, TimeUnit arg1) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unlock() {
		String uuid = local.get();
		System.out.println(Thread.currentThread().getName()+"--"+uuid);
		Jedis jedis = RedisPool.getJedis();
		boolean keyDel = RedisUtil.releaseDistributedLock(jedis, lockName, uuid);
		jedis.close();
		if (keyDel) {
			System.out.println("解锁成功");
		} else {
			System.out.println("解锁失败");
		}

	}

}
