package com.yjk.app.aspect;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.yjk.app.annotation.DistributedLock;
import com.yjk.app.redis.lock.RedisLock;

@Aspect
@Component
public class RedisLockAspect {


	
	@Pointcut("@annotation(com.yjk.app.annotation.DistributedLock)")
	public void annotationPointCut() {}
	
	@Around("annotationPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		MethodSignature signature =(MethodSignature)point.getSignature();
		Method method = signature.getMethod();
		Type type = method.getAnnotatedReceiverType().getType();
		String name = method.getName();
		DistributedLock r = method.getAnnotation(DistributedLock.class);
		Integer lockTime = r.lockTime();
		RedisLock redisLook = new RedisLock(type.getTypeName()+name,lockTime);
		redisLook.lock();
		Object result = point.proceed();
		redisLook.unlock();
		return result;
		
	}
}
