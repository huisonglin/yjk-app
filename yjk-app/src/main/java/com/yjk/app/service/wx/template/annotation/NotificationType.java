package com.yjk.app.service.wx.template.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 通知类型注解
 * @author huisonglin
 *
 */
@Documented  
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.TYPE) 
@Component
public @interface NotificationType {

	public int type();
}
