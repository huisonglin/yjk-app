/**
 * 
 */package com.yjk.app.dto;
/** 
* @author : 刘尊亮
* @date 创建时间：2019年2月26日 下午3:16:25 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
/**
 * @author Administrator
 *
 */
public class InfoMatchDTO {

	private Long infoId;
	
	private String address;
	
	private String deviceName;
	
	private Long selfMemberId;
	
	private Integer type;
	//机型ID
	private Long modeId;
	//二级机型ID
	private Long twoStageModeId;
	//规格ID
	private Long specId;
	
	private Double longitude; //经度
	
	private Double latitude; //纬度
	
	
	
	public Long getInfoId() {
		return infoId;
	}
	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public Long getSelfMemberId() {
		return selfMemberId;
	}
	public void setSelfMemberId(Long selfMemberId) {
		this.selfMemberId = selfMemberId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
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
	public Long getSpecId() {
		return specId;
	}
	public void setSpecId(Long specId) {
		this.specId = specId;
	}
	
	
}
