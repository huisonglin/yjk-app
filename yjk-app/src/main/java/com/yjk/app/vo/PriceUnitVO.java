package com.yjk.app.vo;

public class PriceUnitVO {

	//主键ID
	private Long id;
	
    //单位名称
   private  String unitName;
  
	 //燃油负责方
   private  String fuelManager;
   
   // 1車主油 2工地油
   private Integer type;

   
   
	
	public Integer getType() {
	return type;
	}

public void setType(Integer type) {
	this.type = type;
}

	public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

	public String getUnitName() {
		return unitName;
	}
	
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	public String getFuelManager() {
		return fuelManager;
	}
	
	public void setFuelManager(String fuelManager) {
		this.fuelManager = fuelManager;
	}
	   
   
}
