package com.yjk.app.common;

public enum TemplateEnum {

	

	PAY("支付",1),REFUND("退款",2);
	
	String name;
	
	Integer value;
	
    // 构造方法  
    private TemplateEnum(String name, int value) {  
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
