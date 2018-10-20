package com.yjk.app.entity;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;

@Table(name = "`yjk_spec`")
public class SpecDO {

	@Id
	@KeySql(useGeneratedKeys=true)
	private Long id;
	
	private String name;
	
	private Long twoStageModeId;
	
	private String brand;
	

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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	

	

	
	
	
	
}
