package com.yjk.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;



/**
 * 设备求购表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-07 11:44:59
 */
@Table(name = "`yjk_device_seek_to_purchase_info`")
public class DeviceSeekToPurchaseInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	@Id
	@KeySql(useGeneratedKeys=true)
	private Long id;
	//会员ID
	private Long memberId;
	//名称
	private String name;
	//二级机型ID
	private BigDecimal twoStageModeId;
	//机型ID
	private Long modeId;
	//规格ID
	private Long specId;
	//求购地点
	private String adress;
	//纬度
	private Double latitude;
	//成色要求
	private String useDegree;
	//经度
	private Double longitude;
	//期望价格
	private BigDecimal expectedPrice;
	//备注
	private String remark;
	//1手动发布 2语音发布
	private Integer type;
	//语音内容
	private String voice;
	//1上架 2下架 3删除
	private Integer status;
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
	 * 设置：二级机型ID
	 */
	public void setTwoStageModeId(BigDecimal twoStageModeId) {
		this.twoStageModeId = twoStageModeId;
	}
	/**
	 * 获取：二级机型ID
	 */
	public BigDecimal getTwoStageModeId() {
		return twoStageModeId;
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
	 * 设置：求购地点
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}
	/**
	 * 获取：求购地点
	 */
	public String getAdress() {
		return adress;
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
	 * 设置：成色要求
	 */
	public void setUseDegree(String useDegree) {
		this.useDegree = useDegree;
	}
	/**
	 * 获取：成色要求
	 */
	public String getUseDegree() {
		return useDegree;
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
	 * 设置：1上架 2下架 3删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：1上架 2下架 3删除
	 */
	public Integer getStatus() {
		return status;
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
