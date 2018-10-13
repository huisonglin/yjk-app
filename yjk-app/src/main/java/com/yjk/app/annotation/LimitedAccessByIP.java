package com.yjk.app.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LimitedAccessByIP {
	 int timesOfDay() default -1;  //默认不限制次数 
	 int EachInterva() default 60;  //默认每次的间隔为60秒
	 String key();  //key
}
