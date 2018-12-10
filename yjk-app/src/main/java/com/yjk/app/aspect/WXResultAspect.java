package com.yjk.app.aspect;


import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.yjk.app.util.ParameterHelpUtil;
import com.yjk.app.util.R;
import com.yjk.app.vo.ParameterDescVO;

@Aspect
@Configuration
public class WXResultAspect {



    @Around("execution(* com.yjk.app.controller.**.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
    	HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    	ParameterDescVO desc = ParameterHelpUtil.getParams(req);
    	Object result = point.proceed();
    	if(result instanceof R) {
    		R r =(R)result;
    		r.put("paramsDesc", desc);
    	}
    	return result;
    	
    }

}
