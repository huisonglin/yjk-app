package com.yjk.app.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.yjk.app.annotation.LimitedAccessByToken;
import com.yjk.app.util.LimitUtil;

/**
 * 通过token限制接口访问评率
 * @author huisonglin
 *
 */
@Component
public class LimitedAccessByTokenInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private LimitUtil limitUtil;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LimitedAccessByToken annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(LimitedAccessByToken.class);
        }else{
            return true;
        }

        if(annotation == null){
            return true;
        }
        return limitUtil.limitByToken(request, annotation);
    }


   
    

}
