/**
 * 
 */package com.yjk.app.dto;
/** 
* @author : 刘尊亮
* @date 创建时间：2019年3月4日 下午3:01:54 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
/**
 * @author Administrator
 *
 */
public class MyCollectionDTO {
	
	private Long memberId;
	
	private String identify;
	
	private Integer pageNo;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	
}
