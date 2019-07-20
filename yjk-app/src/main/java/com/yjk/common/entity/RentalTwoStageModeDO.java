package com.yjk.common.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;

@Table(name = "`yjk_rental_two_stage_mode`")
public class RentalTwoStageModeDO {

	@Id
	@KeySql(useGeneratedKeys=true)
	private Long id;
	
	private String name;
	
	private  Long rentalModelId;

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

	public Long getRentalModelId() {
		return rentalModelId;
	}

	public void setRentalModelId(Long rentalModelId) {
		this.rentalModelId = rentalModelId;
	}
	
}
