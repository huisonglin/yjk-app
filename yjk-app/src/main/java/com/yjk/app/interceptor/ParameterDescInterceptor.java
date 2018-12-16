package com.yjk.app.interceptor;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ParameterDescInterceptor implements HandlerInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
    	Map<String, String[]> parameterMap = req.getParameterMap();
    	Set<String> keySet = parameterMap.keySet();
    	logger.info("------------------------------您本地传入的参数start----------------");
    	for (String key : keySet) {
    		logger.info("参数名-----"+key+"----参数值-----------"+ArrayUtils.toString(parameterMap.get(key)));
		}
    	logger.info("------------------------------您本地传入的参数为end----------------");
        return true;
    }

}
