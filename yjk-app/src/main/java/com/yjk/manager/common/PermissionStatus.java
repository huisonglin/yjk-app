package com.yjk.manager.common;

/******
 * 权限状态
 * @author Mr hui
 *
 */
public enum PermissionStatus {
	
	ON("启用", 1), OFF("禁用", 2);
	
	private String name;
	private int value;
	
	private PermissionStatus(String name, int value){
		this.name = name;
		this.value = value;
	}
	
	// 普通方法
	public static String getName(int value) {
		for (PermissionStatus u : PermissionStatus.values()) {
			if (u.getValue() == value) {
				return u.name;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
