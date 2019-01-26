package com.yjk.app.controller.bind;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import com.yjk.app.util.DatetimeUtil;

public class StringConverterDate implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		if("请选择".equals(source)) {
			return null;
		}
		Date target = null;
		if(source != null && !"".equals(source)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				target = sdf.parse(source);
				return target;
			} catch (ParseException e) {
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
				try {
				target = sdf2.parse(source);
				return target;
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if("undefined".equals(source)) {
			return null;
		}
		return null;
	}

}
