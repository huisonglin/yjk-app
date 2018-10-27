package com.yjk.app.vo;

import java.util.List;

public class SpecVO {

	
	private Long id;
	
	private String name;
	
	private Long twoStageModeId;
	
	private String[] brands;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTwoStageModeId() {
		return twoStageModeId;
	}

	public void setTwoStageModeId(Long twoStageModeId) {
		this.twoStageModeId = twoStageModeId;
	}

	public String[] getBrands() {
		return brands;
	}

	public void setBrands(String[] brands) {
		this.brands = brands;
	}

	
	
	
}
