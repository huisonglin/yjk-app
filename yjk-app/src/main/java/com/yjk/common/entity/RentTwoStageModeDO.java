package com.yjk.common.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;

@Table(name = "`yjk_rent_two_stage_model`")
public class RentTwoStageModeDO {

	@Id
	@KeySql(useGeneratedKeys=true)
	private Long id;
	
	private String name;
	
	private Long rentModelId;

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

	public Long getRentModelId() {
		return rentModelId;
	}

	public void setRentModelId(Long rentModelId) {
		this.rentModelId = rentModelId;
	}
	
	
}
