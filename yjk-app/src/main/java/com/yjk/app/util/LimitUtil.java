package com.yjk.app.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.yjk.app.annotation.DistributedLock;
import com.yjk.app.annotation.LimitedAccessByIP;
import com.yjk.app.annotation.LimitedAccessByToken;
import com.yjk.app.exception.RRException;
import com.yjk.app.interceptor.AuthorizationInterceptor;

@Component
public class LimitUtil {

	@Autowired
	ValueOperations<String, String> valueOperations;
	
	@DistributedLock
	public boolean LimitedAccessByIp(HttpServletRequest request, LimitedAccessByIP annotation) {
		String ipAddr = IPUtil.getIpAddr(request);
        String key = annotation.key();
        StringBuilder builder = new StringBuilder("limet_");
        builder.append(key);
        builder.append("_");
        builder.append(ipAddr);
        String value = valueOperations.get(builder.toString());
        if(value == null) {  
        	Integer timesOfDay = annotation.timesOfDay();
        	if(timesOfDay != -1) { //-1表示每天不限次数
            	String countName = getCountName(key, ipAddr);
            	String count = valueOperations.get(countName);
            	if(count == null) {
            		count="1";
            		valueOperations.set(countName, count,DatetimeUtil.RemainderOfTheDay(),TimeUnit.SECONDS);
            	}else {
            		if(Integer.parseInt(count) >= timesOfDay) {
            			throw new RRException("您今天的访问次数太高了,您明天再来吧（每天只允许访问"+timesOfDay+"次哦）");
            		}else {
            			count=count+1;
            			valueOperations.increment(countName, 1);
            		}
            	}
        	}
        	Date date = new Date();
        	Long EachInterva =(long) (annotation.EachInterva()*1000);
        	Long total = date.getTime()+EachInterva;
        	valueOperations.set(builder.toString(),total.toString(),annotation.EachInterva(),TimeUnit.SECONDS);  
        	return true;
        }else {
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
    
	@DistributedLock
	public boolean limitByToken(HttpServletRequest request, LimitedAccessByToken annotation) {
		String userId = (String)request.getAttribute(AuthorizationInterceptor.MemberId);
        String key = annotation.key();
        StringBuilder builder = new StringBuilder("limet_");
        builder.append(key);
        builder.append("_");
        builder.append(userId);
        String value = valueOperations.get(builder.toString());
        if(value == null) {        	Integer timesOfDay = annotation.timesOfDay();
    	if(timesOfDay != -1) { //-1表示每天不限次数
        	String countName = getCountName(key, userId);
        	String count = valueOperations.get(countName);
        	if(count == null) {
        		count="1";
        		valueOperations.set(countName, count,DatetimeUtil.RemainderOfTheDay(),TimeUnit.SECONDS);
        	}else {
        		if(Integer.parseInt(count) >= timesOfDay) {
        			throw new RRException("您今天的访问次数太高了,您明天再来吧（每天只允许访问"+timesOfDay+"次哦）");
        		}else {
        			count=count+1;
        			valueOperations.increment(countName, 1);
        		}
        	}
    	}
    	Date date = new Date();
    	Long EachInterva =(long) (annotation.EachInterva()*1000);
    	Long total = date.getTime()+EachInterva;
    	valueOperations.set(builder.toString(),total.toString(),annotation.EachInterva(),TimeUnit.SECONDS);  
    	return true;
    }else {
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
	
    private String getCountName(String key,String limitType) {
    	StringBuilder builder = new StringBuilder("limet_");
        builder.append(key);
        builder.append("_");
        builder.append(limitType);
        builder.append("_Count");
        return builder.toString();
    	
    }
    
}
