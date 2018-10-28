package com.yjk.app.util;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;
import com.yjk.app.annotation.LimitedAccessByIP;
import com.yjk.app.annotation.LimitedAccessByToken;
import com.yjk.app.exception.RRException;
import com.yjk.app.interceptor.AuthorizationInterceptor;

@Component
public class LimitUtil {

	@SuppressWarnings("rawtypes")
	@Autowired
	RedisTemplate redisTemplate;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean LimitedAccessByIp(HttpServletRequest request, LimitedAccessByIP annotation) {
		String ipAddr = IPUtil.getIpAddr(request);
        String key = annotation.key();
        StringBuilder builder = new StringBuilder("limet_");
        builder.append(key);
        builder.append("_");
        builder.append(ipAddr);
        redisTemplate.execute(new SessionCallback() {
			@Override
			public Object execute(RedisOperations operations) throws DataAccessException {
				operations.watch(builder.toString());
		        String value = (String)operations.opsForValue().get(builder.toString());
            	String countName = getCountName(key, ipAddr);
            	String count = (String) operations.opsForValue().get(countName);
		        operations.multi();
		        if(value == null) {  
		        	Integer timesOfDay = annotation.timesOfDay();
		        	if(timesOfDay != -1) { //-1表示每天不限次数
		            	if(count == null) {
		            		count="1";
		            		operations.opsForValue().set(countName, count,DatetimeUtil.RemainderOfTheDay(),TimeUnit.SECONDS);
		            	}else {
		            		if(Integer.parseInt(count) >= timesOfDay) {
		            			throw new RRException("您今天的访问次数太高了,您明天再来吧（每天只允许访问"+timesOfDay+"次哦）");
		            		}else {
		            			count=count+1;
		            			operations.opsForValue().increment(countName, 1);
		            		}
		            	}
		        	}
		        	Date date = new Date();
		        	Long EachInterva =(long) (annotation.EachInterva()*1000);
		        	Long total = date.getTime()+EachInterva;
		        	operations.opsForValue().set(builder.toString(),total.toString(),annotation.EachInterva(),TimeUnit.SECONDS);
		        	List exec = operations.exec();
		        	if(exec == null||exec.size()<1) {
		        		throw new RRException("请勿重复操作");
		        	}
		        	return true;
		        }else {
		        	operations.exec();
		        	Date date = new Date();
		        	Integer  remainingSeconds =(int) ((Long.parseLong(value)-date.getTime())/1000);
		        	if(remainingSeconds<=60) {
		            	throw new RRException("您访问频率太高啦,请于"+remainingSeconds+"秒后再来访问吧！");
		        	}else {
		        		int remainingMinutes = remainingSeconds/60;
		        		throw new RRException("您访问频率太高啦,请于"+remainingMinutes+"分钟后再来访问吧！");
		        	}

		        }
			}
		});
		return true;
	}
    
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean limitByToken(HttpServletRequest request, LimitedAccessByToken annotation) {
		String userId = (String)request.getAttribute(AuthorizationInterceptor.MemberId);
        String key = annotation.key();
        StringBuilder builder = new StringBuilder("limet_");
        builder.append(key);
        builder.append("_");
        builder.append(userId);
        redisTemplate.execute(new SessionCallback() {
			@Override
			public Object execute(RedisOperations operations) throws DataAccessException {
				operations.watch(builder.toString());
		        String value = (String)operations.opsForValue().get(builder.toString());
            	String countName = getCountName(key, userId);
            	String count = (String) operations.opsForValue().get(countName);
		        operations.multi();
		        if(value == null) {  
		        	Integer timesOfDay = annotation.timesOfDay();
		        	if(timesOfDay != -1) { //-1表示每天不限次数
		            	if(count == null) {
		            		count="1";
		            		operations.opsForValue().set(countName, count,DatetimeUtil.RemainderOfTheDay(),TimeUnit.SECONDS);
		            	}else {
		            		if(Integer.parseInt(count) >= timesOfDay) {
		            			throw new RRException("您今天的访问次数太高了,您明天再来吧（每天只允许访问"+timesOfDay+"次哦）");
		            		}else {
		            			count=count+1;
		            			operations.opsForValue().increment(countName, 1);
		            		}
		            	}
		        	}
		        	Date date = new Date();
		        	Long EachInterva =(long) (annotation.EachInterva()*1000);
		        	Long total = date.getTime()+EachInterva;
		        	operations.opsForValue().set(builder.toString(),total.toString(),annotation.EachInterva(),TimeUnit.SECONDS);
		        	List exec = operations.exec();
		        	if(exec == null||exec.size()<1) {
		        		throw new RRException("请勿重复操作");
		        	}
		        	return true;
		        }else {
		        	operations.exec();
		        	Date date = new Date();
		        	Integer  remainingSeconds =(int) ((Long.parseLong(value)-date.getTime())/1000);
		        	if(remainingSeconds<=60) {
		            	throw new RRException("您访问频率太高啦,请于"+remainingSeconds+"秒后再来访问吧！");
		        	}else {
		        		int remainingMinutes = remainingSeconds/60;
		        		throw new RRException("您访问频率太高啦,请于"+remainingMinutes+"分钟后再来访问吧！");
		        	}
		        }
			}
        });
        return true;
	}
	
    private String getCountName(String key,String limitType) {
    	StringBuilder builder = new StringBuilder("limet_");
        builder.append(key);
        builder.append("_");
        builder.append(limitType);
        builder.append("_Count");
        return builder.toString();
    	
    }
    
}
