package com.yjk.app.common;

public enum PublishingTypeEnum {

	RENT_OUT("发布出租",1),RENTAL_IN_NEED("发布求租",2),SELL("发布出售",3),SEEK_TO_PURCHASE("发布求购",4);
	
	String name;
	
	Integer value;
	
    // 构造方法  
    private PublishingTypeEnum(String name, int value) {  
        this.name = name;  
        this.value = value;  
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}  
	
    
}
