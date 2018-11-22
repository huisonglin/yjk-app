package com.yjk.app.service.wx.template.config;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.yjk.app.service.wx.template.annotation.NotificationType;

@Configuration
public class TemplateMessageConfig implements ApplicationContextAware{

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	@Bean
	public Map<String, Object> beansWithAnnotationMap(){
		return this.applicationContext.getBeansWithAnnotation(NotificationType.class); 
	}

}
