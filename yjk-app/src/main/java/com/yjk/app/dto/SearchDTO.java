package com.yjk.app.dto;

public class SearchDTO {
	//页码
	private Integer pageNo;
	//类型
	private Integer type;
	//距离
	private Double distance;
	//机型ID
	private Long modeId;
	//二级机型ID
	private Long twoStageModeId;
	//规格ID
	private Long specId;
	//用户ID
	private String memberId;
	//位置信息 
	private PositionDTO positionDTO;
	
	private Integer pageSize;
	
	
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
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
	public PositionDTO getPositionDTO() {
		return positionDTO;
	}
	public void setPositionDTO(PositionDTO positionDTO) {
		this.positionDTO = positionDTO;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	
}
