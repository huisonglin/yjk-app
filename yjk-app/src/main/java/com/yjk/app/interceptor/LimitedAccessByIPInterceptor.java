package com.yjk.app.interceptor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.yjk.app.annotation.LimitedAccessByIP;
import com.yjk.app.util.LimitUtil;

/**
 * 通过IP限制接口访问频率
 * @author huisonglin
 *
 */
@Component
public class LimitedAccessByIPInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	LimitUtil limitUtil;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LimitedAccessByIP annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(LimitedAccessByIP.class);
        }else{
            return true;
        }

        if(annotation == null){
            return true;
        }
        
        return limitUtil.LimitedAccessByIp(request, annotation);
    }

   
}
