package com.yjk.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;



/**
 * 工程求租表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-07 11:44:34
 */
@Table(name = "`yjk_device_rental_in_need_info`")
public class DeviceRentalInNeedInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	@Id
	@KeySql(useGeneratedKeys=true)
	private Long id;
	//会员ID
	private Long memberId;
	//施工地点
	private String adress;
	//名称
	private String name;
	//经度
	private Double longitude;
	//1未上架 2已上架 3已删除
	private Integer status;
	//1手动发布 2语音发布
	private Integer type;
	//语音类容
	private String voice;
	//纬度
	private Double latitude;
	//机型ID
	private Long modeId;
	//二级机型ID
	private Long twoStageModeId;
	//规格ID
	private Long specId;
	//求购数量
	private Integer needNumber;
	//工期
	private String term;
	//进场时间
	private Date inTime;
	//备注
	private String remark;
	//是否紧急
	private Integer isEmergent;
	//运输费
	private String transportation;
	//税费
	private String taxation;
	//设备进场付款
	private String equipmentArrivalPayment;
	//按月进度付款
	private String monthlyPayment;
	//设备退场结清尾款
	private String exitPayment;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	
	//发布时间
	private Date newstime;
	
	

	public Date getNewstime() {
		return newstime;
	}
	public void setNewstime(Date newstime) {
		this.newstime = newstime;
	}
	/**
	 * 设置：主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：会员ID
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：会员ID
	 */
	public Long getMemberId() {
		return memberId;
	}
	/**
	 * 设置：施工地点
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}
	/**
	 * 获取：施工地点
	 */
	public String getAdress() {
		return adress;
	}
	/**
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：经度
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	/**
	 * 获取：经度
	 */
	public Double getLongitude() {
		return longitude;
	}
	/**
	 * 设置：1未上架 2已上架 3已删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：1未上架 2已上架 3已删除
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：1手动发布 2语音发布
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：1手动发布 2语音发布
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：语音类容
	 */
	public void setVoice(String voice) {
		this.voice = voice;
	}
	/**
	 * 获取：语音类容
	 */
	public String getVoice() {
		return voice;
	}
	/**
	 * 设置：纬度
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	/**
	 * 获取：纬度
	 */
	public Double getLatitude() {
		return latitude;
	}
	/**
	 * 设置：机型ID
	 */
	public void setModeId(Long modeId) {
		this.modeId = modeId;
	}
	/**
	 * 获取：机型ID
	 */
	public Long getModeId() {
		return modeId;
	}
	/**
	 * 设置：二级机型ID
	 */
	public void setTwoStageModeId(Long twoStageModeId) {
		this.twoStageModeId = twoStageModeId;
	}
	/**
	 * 获取：二级机型ID
	 */
	public Long getTwoStageModeId() {
		return twoStageModeId;
	}
	/**
	 * 设置：规格ID
	 */
	public void setSpecId(Long specId) {
		this.specId = specId;
	}
	/**
	 * 获取：规格ID
	 */
	public Long getSpecId() {
		return specId;
	}
	/**
	 * 设置：求购数量
	 */
	public void setNeedNumber(Integer needNumber) {
		this.needNumber = needNumber;
	}
	/**
	 * 获取：求购数量
	 */
	public Integer getNeedNumber() {
		return needNumber;
	}
	/**
	 * 设置：工期
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	/**
	 * 获取：工期
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * 设置：进场时间
	 */
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	/**
	 * 获取：进场时间
	 */
	public Date getInTime() {
		return inTime;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：是否紧急
	 */
	public void setIsEmergent(Integer isEmergent) {
		this.isEmergent = isEmergent;
	}
	/**
	 * 获取：是否紧急
	 */
	public Integer getIsEmergent() {
		return isEmergent;
	}
	/**
	 * 设置：运输费
	 */
	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}
	/**
	 * 获取：运输费
	 */
	public String getTransportation() {
		return transportation;
	}
	/**
	 * 设置：税费
	 */
	public void setTaxation(String taxation) {
		this.taxation = taxation;
	}
	/**
	 * 获取：税费
	 */
	public String getTaxation() {
		return taxation;
	}
	/**
	 * 设置：设备进场付款
	 */
	public void setEquipmentArrivalPayment(String equipmentArrivalPayment) {
		this.equipmentArrivalPayment = equipmentArrivalPayment;
	}
	/**
	 * 获取：设备进场付款
	 */
	public String getEquipmentArrivalPayment() {
		return equipmentArrivalPayment;
	}
	/**
	 * 设置：按月进度付款
	 */
	public void setMonthlyPayment(String monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	/**
	 * 获取：按月进度付款
	 */
	public String getMonthlyPayment() {
		return monthlyPayment;
	}
	/**
	 * 设置：设备退场结清尾款
	 */
	public void setExitPayment(String exitPayment) {
		this.exitPayment = exitPayment;
	}
	/**
	 * 获取：设备退场结清尾款
	 */
	public String getExitPayment() {
		return exitPayment;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
