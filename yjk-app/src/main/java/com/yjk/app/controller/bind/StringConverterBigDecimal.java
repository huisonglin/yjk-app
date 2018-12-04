package com.yjk.app.controller.bind;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class StringConverterBigDecimal implements Converter<String, BigDecimal>{

	@Override
	public BigDecimal convert(String source) {
		if(StringUtils.isBlank(source)) {
			return null;
		}
		return new BigDecimal(source);
	}

}
