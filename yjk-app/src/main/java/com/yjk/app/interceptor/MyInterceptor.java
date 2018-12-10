package com.yjk.app.interceptor;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器
 *
 * @author wuwei
 * @date 2017-8-26 9:59:19
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
    	Map<String, String[]> parameterMap = req.getParameterMap();
    	Set<String> keySet = parameterMap.keySet();
    	System.out.println("------------------------------您本地传入的参数start----------------");
    	for (String key : keySet) {
			
			System.out.println("参数名-----"+key+"----参数值-----------"+ArrayUtils.toString(parameterMap.get(key)));
		}
    	System.out.println("------------------------------您本地传入的参数为end----------------");
        System.out.println("拦截器：预处理...");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView mav) throws Exception {
        System.out.println("拦截器：后处理...");
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception e) throws Exception {
        System.out.println("拦截器：结束...");
    }
}
