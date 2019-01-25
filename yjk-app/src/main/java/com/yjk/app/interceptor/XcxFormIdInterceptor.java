package com.yjk.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//获取小程序的formId
@Component
public class XcxFormIdInterceptor extends HandlerInterceptorAdapter{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public static String FORM_ID= "form_id_";
	
	@Autowired
	ValueOperations<String, String> valueOperations;
	
	@Autowired
	ListOperations<String, Object> listOperations;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("开始获取用户的小程序的form_id");
		String formId = request.getParameter("formId");
		String userId = (String)request.getAttribute(AuthorizationInterceptor.MemberId);
		if (StringUtils.isNotBlank(formId) && StringUtils.isNotBlank(userId)) {
			listOperations.leftPush(FORM_ID+userId, formId);
		}
		return true;
	}

}
