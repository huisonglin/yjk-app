package com.yjk.common.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;


@Table(name = "`yjk_rental_spec`")
public class RentalSpecDO {

	@Id
	@KeySql(useGeneratedKeys=true)
	private Long id;
	
	private String name;
	
	private Long RentalTwoStageModeId;

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

	public Long getRentalTwoStageModeId() {
		return RentalTwoStageModeId;
	}

	public void setRentalTwoStageModeId(Long rentalTwoStageModeId) {
		RentalTwoStageModeId = rentalTwoStageModeId;
	}
	
	
}
