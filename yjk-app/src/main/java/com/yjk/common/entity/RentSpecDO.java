package com.yjk.common.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;

@Table(name = "`yjk_rent_spec`")
public class RentSpecDO {

	@Id
	@KeySql(useGeneratedKeys=true)
	private Long id;
	
	private String name;
	
	private Long rentTwoStageModeId;

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

	public Long getRentTwoStageModeId() {
		return rentTwoStageModeId;
	}

	public void setRentTwoStageModeId(Long rentTwoStageModeId) {
		this.rentTwoStageModeId = rentTwoStageModeId;
	}
	
	
}
