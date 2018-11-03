package com.yjk.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;



/**
 * 设备出租信息表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-07 11:44:15
 */
@Table(name = "`yjk_device_rent_out_info`")
public class DeviceRentOutInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	@Id
	@KeySql(useGeneratedKeys=true)
	private Long id;
	//会员ID
	private Long memberId;
	//设备ID
	private Long deviceId;
	//出租地址
	private String address;
	
	private String addressDetail;
	//经度
	private Double longitude;
	//纬度
	private Double latitude;
	//1手动发布 2语音发布
	private Integer type;
	//语音内容
	private String voice;
	//1未上架 2已上架 3已删除
	private Integer status;
	//出租备注
	private String remark;
	//活跃度
	private Integer liveness;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//发布时间
	private Date newstime;

	
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
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
	 * 设置：设备ID
	 */
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * 获取：设备ID
	 */
	public Long getDeviceId() {
		return deviceId;
	}
	/**
	 * 设置：出租地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：出租地址
	 */
	public String getAddress() {
		return address;
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
	 * 设置：语音内容
	 */
	public void setVoice(String voice) {
		this.voice = voice;
	}
	/**
	 * 获取：语音内容
	 */
	public String getVoice() {
		return voice;
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
	 * 设置：出租备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：出租备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：活跃度
	 */
	public void setLiveness(Integer liveness) {
		this.liveness = liveness;
	}
	/**
	 * 获取：活跃度
	 */
	public Integer getLiveness() {
		return liveness;
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
