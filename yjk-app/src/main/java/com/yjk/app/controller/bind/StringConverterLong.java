package com.yjk.app.controller.bind;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class StringConverterLong implements Converter<String, Long>{

	@Override
	public Long convert(String source) {
		if(StringUtils.isBlank(source)) {
			return null;
		}
		if("\"\"".equals(source)) {
			return null;
		}
		if("请选择".equals(source)) {
			return null;
		}
		if("undefined".equals(source)) {
			return null;
		}
		return Long.valueOf(source);
	}

}
