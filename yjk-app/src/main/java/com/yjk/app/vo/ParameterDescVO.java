package com.yjk.app.vo;

import java.util.List;

public class ParameterDescVO {

	private String desc = "本次请求服务器接收到您传入的参数";
	
	private List<ParameterHelpVO> parameterHelpVO;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<ParameterHelpVO> getParameterHelpVO() {
		return parameterHelpVO;
	}

	public void setParameterHelpVO(List<ParameterHelpVO> parameterHelpVO) {
		this.parameterHelpVO = parameterHelpVO;
	}
	
	
}
