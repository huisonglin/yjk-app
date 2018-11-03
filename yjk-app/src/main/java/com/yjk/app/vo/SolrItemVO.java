package com.yjk.app.vo;

import java.util.Date;

public class SolrItemVO {
	//主键id
	String id;
	//名称   
	String name;	
	//图片地址
	String url;
	//经纬度
	String info_position;
	//星级		
	Integer starLeve;
	//列表类型
	Integer popularity;   //1设备出租 2设备出售  3工程发布  4紧急求购
	//规格ID
	Long specId;
	//机型ID
	Long modeId;
	//二级机型ID
	Long twoStageModeId;
	//发布日期
	Date last_modified;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getInfo_position() {
		return info_position;
	}
	public void setInfo_position(String info_position) {
		this.info_position = info_position;
	}
	public Integer getStarLeve() {
		return starLeve;
	}
	public void setStarLeve(Integer starLeve) {
		this.starLeve = starLeve;
	}
	public Integer getPopularity() {
		return popularity;
	}
	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}
	public Long getSpecId() {
		return specId;
	}
	public void setSpecId(Long specId) {
		this.specId = specId;
	}
	public Long getModeId() {
		return modeId;
	}
	public void setModeId(Long modeId) {
		this.modeId = modeId;
	}
	public Long getTwoStageModeId() {
		return twoStageModeId;
	}
	public void setTwoStageModeId(Long twoStageModeId) {
		this.twoStageModeId = twoStageModeId;
	}
	public Date getLast_modified() {
		return last_modified;
	}
	public void setLast_modified(Date last_modified) {
		this.last_modified = last_modified;
	}
	
	
}
