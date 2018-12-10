package com.yjk.app.controller.bind;

import org.springframework.core.convert.converter.Converter;

public class StringConverterString implements Converter<String, String>{

	@Override
	public String convert(String source) {
		if("undefined".equals(source)) {
			return null;
		}
		return source;
	}

}
