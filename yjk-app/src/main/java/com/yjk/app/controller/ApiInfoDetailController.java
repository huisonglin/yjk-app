package com.yjk.app.controller;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjk.app.annotation.Login;
import com.yjk.app.common.Constants;
import com.yjk.app.service.InfoDetailService;
import com.yjk.app.util.JwtUtils;
import com.yjk.app.util.R;
import com.yjk.app.validator.Assert;


@RestController
@RequestMapping("/app/info")
public class ApiInfoDetailController {

	@Autowired
	InfoDetailService infoDetailService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//@Login
	@RequestMapping("/detail")
	public R detail(Long id,Integer infoType,Long memberId,HttpServletRequest request) throws Exception {
		String mId = getMemberId(request);
		if(StringUtils.isNotBlank(mId)) {
			memberId = Long.valueOf(mId);
		}
		Assert.isNull(id, "id不能为空");
		Assert.isNull(infoType, "信息类型不能为空");
		Object infoDetail = infoDetailService.infoDetail(id, infoType);
		infoDetailService.dealDetail(infoDetail,memberId);
		return R.ok().put("info", infoDetail);
	}
	
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	ValueOperations<String, String> valueOperations;
	
	private String getMemberId(HttpServletRequest request) throws Exception {
		try {
			String memberId = null;
			String token = request.getParameter("token");
			if(StringUtils.isNotBlank(token)) {
				String info = jwtUtils.getClaimByToken(token).getSubject();
				if(StringUtils.isNotBlank(info)) {
				       if(info.contains("#")) { //APP登录
				           String[] sat = info.split("#");
				           String  terminalValue= valueOperations.get(Constants.TERMINAL+"_"+sat[0]);
				           memberId = terminalValue;
				       }else {
				    	   memberId = info;
				       }
				}
			}
			return memberId;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
}
