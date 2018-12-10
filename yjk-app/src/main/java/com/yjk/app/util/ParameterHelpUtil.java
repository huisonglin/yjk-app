package com.yjk.app.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;

import com.yjk.app.vo.ParameterDescVO;
import com.yjk.app.vo.ParameterHelpVO;

public class ParameterHelpUtil {

	
	public static ParameterDescVO getParams(HttpServletRequest req) {
		Map<String, String[]> parameterMap = req.getParameterMap();
    	Set<String> keySet = parameterMap.keySet();
    	ParameterDescVO desc = new ParameterDescVO();
    	List<ParameterHelpVO> vos = new ArrayList<>();

    	for (String key : keySet) {
    		ParameterHelpVO p = new ParameterHelpVO();
    		p.setParameterName(key);
    		p.setParameterValue(ArrayUtils.toString(parameterMap.get(key)));
    		vos.add(p);
		}
    	desc.setParameterHelpVO(vos);
		return desc;
	}
}
