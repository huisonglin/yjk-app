package com.yjk.app.common;

public enum OrderStatusEnum {

	UNPAID("未支付",1), PAYMENT("已支付",2), REFUND("已退款",3);
	
	String name;
	
	Integer value;
	
    // 构造方法  
    private OrderStatusEnum(String name, int value) {  
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
