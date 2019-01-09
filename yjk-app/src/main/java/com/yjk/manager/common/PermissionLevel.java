package com.yjk.manager.common;

/******
 * 权限层级
 * @author Mr hui
 *
 */
public enum PermissionLevel {
	
	TOP("顶级", 1), ELSE("其他", 2);
	
	private String name;
	private int value;
	
	private PermissionLevel(String name, int value){
		this.name = name;
		this.value = value;
	}
	
	// 普通方法
	public static String getName(int value) {
		for (PermissionLevel u : PermissionLevel.values()) {
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
