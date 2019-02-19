package com.yjk.app.util;

import java.io.Serializable;
import java.util.List;

public class PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	// 总页数
	private int total;
	//总条数
	private int totalCount;
	// 列表数据
	private List<?> rows;
	
	private Integer pageSize;
	
	

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 分页
	 * 
	 * @param list
	 *            列表数据
	 * @param totalCount
	 *            总记录数
	 * @param pageSize
	 *            每页记录数
	 * @param currPage
	 *            当前页数
	 */
	public PageUtils(List<?> list, int total) {
		this.rows = list;
		this.total = total;
	}
	
	public PageUtils(List<?> list, int total,int totalCount) {
		this.rows = list;
		this.total = total;
		this.totalCount = totalCount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}
