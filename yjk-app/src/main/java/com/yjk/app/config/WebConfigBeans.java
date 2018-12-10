package com.yjk.app.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.yjk.app.controller.bind.StringConverterBigDecimal;
import com.yjk.app.controller.bind.StringConverterDate;
import com.yjk.app.controller.bind.StringConverterDouble;
import com.yjk.app.controller.bind.StringConverterInterger;
import com.yjk.app.controller.bind.StringConverterLong;
import com.yjk.app.controller.bind.StringConverterString;

@Configuration
public class WebConfigBeans {
	@Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    /**
     * 转换成日期格式
     */
    @PostConstruct
    public void initEditableValidation() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter
            .getWebBindingInitializer();
        if (initializer.getConversionService() != null) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer
            		.getConversionService();
            genericConversionService.addConverter(new StringConverterDate());
            genericConversionService.addConverter(new StringConverterInterger());
            genericConversionService.addConverter(new StringConverterLong());
            genericConversionService.addConverter(new StringConverterDouble());
            genericConversionService.addConverter(new StringConverterString());
            genericConversionService.addConverter(new StringConverterBigDecimal());
            
        }

    }
}
