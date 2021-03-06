package com.yjk.app.interceptor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.yjk.app.resolver.LoginUserHandlerMethodArgumentResolver;
import com.yjk.app.resolver.PositionHandlerMethodArgumentResolver;

/**
 * 添加拦截器
 *
 * @author 吴维
 * @date 2017-8-26 10:25:31
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

	@Autowired
	TimeConsumingInterceptor timeConsumingInterceptor;
	@Autowired
	AuthorizationInterceptor authorizationInterceptor;
	@Autowired
	LimitedAccessByTokenInterceptor limitedAccessByTokenInterceptor;
	@Autowired
	LimitedAccessByIPInterceptor limitedAccessByIPInterceptor;
	@Autowired
	ParameterDescInterceptor parameterDescInterceptor;

	@Autowired
	LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;
	@Autowired
	PositionHandlerMethodArgumentResolver positionHandlerMethodArgumentResolver;
	@Autowired
	XcxFormIdInterceptor xcxFormIdInterceptor;
	
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加自定义拦截器
    	registry.addInterceptor(parameterDescInterceptor).addPathPatterns("/**");
        registry.addInterceptor(timeConsumingInterceptor).addPathPatterns("/**");
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**");
        registry.addInterceptor(limitedAccessByTokenInterceptor).addPathPatterns("/**");
        registry.addInterceptor(limitedAccessByIPInterceptor).addPathPatterns("/**");
        registry.addInterceptor(xcxFormIdInterceptor).addPathPatterns("/**");
        
    }
    
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
        argumentResolvers.add(positionHandlerMethodArgumentResolver);
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

}
