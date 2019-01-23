package com.yjk.app.vo;

import java.util.List;

public class SelectSubTypeVO {

	private String text;
	
	private List<SelectSpecVO> children;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<SelectSpecVO> getChildren() {
		return children;
	}

	public void setChildren(List<SelectSpecVO> children) {
		this.children = children;
	}

	
	
}
