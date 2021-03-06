package com.yjk.app.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yjk.app.util.ParameterHelpUtil;
import com.yjk.app.util.R;
import com.yjk.app.vo.ParameterDescVO;

@ControllerAdvice
public class RRExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ResponseBody
	@ExceptionHandler(RRException.class)
	public R handleRRException(RRException e){
		R r = new R();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());
		r.put("paramsDesc", getParamter());
		return r;
	}

	@ResponseBody
	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return R.error("数据库中已存在该记录").put("paramsDesc", getParamter());
	}

	@ResponseBody
	@ExceptionHandler(BindException.class)
	public R handleBindException(BindException e){
		logger.error(e.getMessage(), e);
		return R.error("参数绑定异常").put("paramsDesc", getParamter());
	}
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){
		logger.error(e.getMessage(), e);
		return R.error().put("paramsDesc", getParamter());
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public String handleBindException(UnauthorizedException e){
		return "error/403";
	}
	
	private ParameterDescVO getParamter() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return ParameterHelpUtil.getParams(req);
	}

}
