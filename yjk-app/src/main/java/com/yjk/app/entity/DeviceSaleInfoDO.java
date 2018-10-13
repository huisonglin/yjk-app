package com.yjk.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;



/**
 * 设备出售信息表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-07 11:44:48
 */
@Table(name = "`yjk_device_sale_info`")
public class DeviceSaleInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	@Id
	@KeySql(useGeneratedKeys=true)
	private Long id;
	//设备ID
	private Long deviceId;
	//1已上架2未上架3已删除
	private Integer status;
	//1手动发布 2语音发布
	private Integer type;
	//位置
	private String adress;
	//设备精度
	private Double longitude;
	//设备纬度
	private Double latitude;
	//语音内容
	private String voice;
	//期望价格
	private BigDecimal expectedPrice;
	//设备描述
	private String remark;
	//主要传动件
	private String drivingGuarantee;
	//泵阀质保期
	private String pumpGuarantee;
	//发动机质保期
	private String engineGuarantee;
	//活跃度
	private Integer liveness;
	//会员ID
	private Long memberId;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;

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
	 * 设置：1已上架2未上架3已删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：1已上架2未上架3已删除
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
	 * 设置：位置
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}
	/**
	 * 获取：位置
	 */
	public String getAdress() {
		return adress;
	}
	/**
	 * 设置：设备精度
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	/**
	 * 获取：设备精度
	 */
	public Double getLongitude() {
		return longitude;
	}
	/**
	 * 设置：设备纬度
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	/**
	 * 获取：设备纬度
	 */
	public Double getLatitude() {
		return latitude;
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
	 * 设置：期望价格
	 */
	public void setExpectedPrice(BigDecimal expectedPrice) {
		this.expectedPrice = expectedPrice;
	}
	/**
	 * 获取：期望价格
	 */
	public BigDecimal getExpectedPrice() {
		return expectedPrice;
	}
	/**
	 * 设置：设备描述
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：设备描述
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：主要传动件
	 */
	public void setDrivingGuarantee(String drivingGuarantee) {
		this.drivingGuarantee = drivingGuarantee;
	}
	/**
	 * 获取：主要传动件
	 */
	public String getDrivingGuarantee() {
		return drivingGuarantee;
	}
	/**
	 * 设置：泵阀质保期
	 */
	public void setPumpGuarantee(String pumpGuarantee) {
		this.pumpGuarantee = pumpGuarantee;
	}
	/**
	 * 获取：泵阀质保期
	 */
	public String getPumpGuarantee() {
		return pumpGuarantee;
	}
	/**
	 * 设置：发动机质保期
	 */
	public void setEngineGuarantee(String engineGuarantee) {
		this.engineGuarantee = engineGuarantee;
	}
	/**
	 * 获取：发动机质保期
	 */
	public String getEngineGuarantee() {
		return engineGuarantee;
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
