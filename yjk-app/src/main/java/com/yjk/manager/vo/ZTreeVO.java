package com.yjk.manager.vo;

/******
 * z-Tree树形结构VO
 * @author ATH
 *
 */
public class ZTreeVO {

	private Long id;
	
	private Long pId;
	
	private String name;
	
	private boolean checked = false;

	private boolean open = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
}
