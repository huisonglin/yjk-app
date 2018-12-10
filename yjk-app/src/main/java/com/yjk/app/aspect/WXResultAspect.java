package com.yjk.app.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.yjk.app.util.R;
import com.yjk.app.vo.ParameterDescVO;
import com.yjk.app.vo.ParameterHelpVO;

@Aspect
@Configuration
public class WXResultAspect {


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Around("execution(* com.yjk.app.controller.**.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
    	HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    	
    	Map<String, String[]> parameterMap = req.getParameterMap();
    	Set<String> keySet = parameterMap.keySet();
    	ParameterDescVO desc = new ParameterDescVO();
    	List<ParameterHelpVO> vos = new ArrayList<>();
    	Object result = point.proceed();
    	for (String key : keySet) {
    		ParameterHelpVO p = new ParameterHelpVO();
    		p.setParameterName(key);
    		p.setParameterValue(ArrayUtils.toString(parameterMap.get(key)));
    		vos.add(p);
		}
    	desc.setParameterHelpVO(vos);
    	if(result instanceof R) {
    		R r =(R)result;
    		r.put("paramsDesc", desc);
    	}
    	return result;
    	
    }
}
