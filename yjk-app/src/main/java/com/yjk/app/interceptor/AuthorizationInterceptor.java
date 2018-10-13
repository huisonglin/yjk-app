package com.yjk.app.interceptor;



import io.jsonwebtoken.Claims;

import com.yjk.app.annotation.Login;
import com.yjk.app.common.Constants;
import com.yjk.app.exception.RRException;
import com.yjk.app.util.JwtUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:38
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
	
    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    ValueOperations<String, String> valueOperations;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    public static  String MemberId = "memberId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Login annotation;
        logger.info("进入授权拦截器。。。");
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        }else{
        	logger.info("无需进行授权。。。");
            return true;
        }

        if(annotation == null){
        	logger.info("无需进行授权。。。");
            return true;
        }
        
        logger.info("执行授权。。。");
        //获取用户凭证
        String token = request.getHeader(jwtUtils.getHeader());
        if(StringUtils.isBlank(token)){
            token = request.getParameter(jwtUtils.getHeader());
        }
        //凭证为空
        if(StringUtils.isBlank(token)){
            throw new RRException(jwtUtils.getHeader() + "不能为空", HttpStatus.UNAUTHORIZED.value());
            
        }       
       Claims claims = jwtUtils.getClaimByToken(token);
       String  info = jwtUtils.getClaimByToken(token).getSubject();
       if(claims == null || jwtUtils.isTokenExpired(claims.getExpiration())){
    	   if(info.contains("#")) {
    		   String[] sat =info.split("#");
    		   valueOperations.set(Constants.TERMINAL+"_"+sat[0], null);
    	   }
    	   throw new RRException("token失效，请重新登录",HttpStatus.UNAUTHORIZED.value());
       } 
 
       if(info.contains("#")) { //APP登录
           String[] sat = info.split("#");
           String  terminalValue= valueOperations.get(Constants.TERMINAL+"_"+sat[0]);
           if(terminalValue != null) {
        	   if(!terminalValue.equals(sat[1])) {
        		   throw new RRException("您已在其他设备登录,若非本人操作请及时修改密码！",HttpStatus.UNAUTHORIZED.value());
        	   }
           }
           request.setAttribute(MemberId, sat[0]);
       }else {  //小程序登录
           request.setAttribute(MemberId, info);
       }

       logger.info("授权成功。。。");
	   return true;
    }
    
}
