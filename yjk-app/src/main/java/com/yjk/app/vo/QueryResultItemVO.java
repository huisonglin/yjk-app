package com.yjk.app.vo;

public class QueryResultItemVO {

	private String id;
	
	private String name;
	
	private String distance;
	
	private String address;
	
	private String pic;
	//信息类型
	private String type;
	
	private Object itemDetail;
	

	public Object getItemDetail() {
		return itemDetail;
	}

	public void setItemDetail(Object itemDetail) {
		this.itemDetail = itemDetail;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
