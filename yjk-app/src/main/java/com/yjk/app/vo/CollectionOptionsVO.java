package com.yjk.app.vo;

public class CollectionOptionsVO {

	Long collectionId;
	
	Long infoId;
	
	Integer infoType;
	
	
	Integer status;

	Long memberId;
	
	

	public Long getCollectionId() {
		return collectionId;
	}


	public void setCollectionId(Long collectionId) {
		this.collectionId = collectionId;
	}


	public Long getMemberId() {
		return memberId;
	}


	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}


	public Long getInfoId() {
		return infoId;
	}


	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}


	public Integer getInfoType() {
		return infoType;
	}


	public void setInfoType(Integer infoType) {
		this.infoType = infoType;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
