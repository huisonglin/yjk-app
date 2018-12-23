package com.yjk.app.resolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.yjk.app.annotation.LoginUser;
import com.yjk.app.interceptor.AuthorizationInterceptor;
import com.yjk.app.service.MemberService;
import com.yjk.common.entity.MemberDO;

@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{

    @Autowired
    private MemberService memberService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(MemberDO.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        //获取用户ID
        Object object = request.getAttribute(AuthorizationInterceptor.MemberId, RequestAttributes.SCOPE_REQUEST);
        if(object == null){
            return null;
        }

        //获取用户信息
        MemberDO member = memberService.selectByPrimaryKey(Long.parseLong(object.toString()));

        return member;
    }

}
