package com.yjk.manager.common;

/******
 * 权限类型
 * @author Mr hui
 *
 */
public enum PermissionType {
	
	MENU("菜单", 1), BUTTON("按钮", 2), ELSE("其他", 3);
	
	private String name;
	private int value;
	
	private PermissionType(String name, int value){
		this.name = name;
		this.value = value;
	}
	
	// 普通方法
	public static String getName(int value) {
		for (PermissionType u : PermissionType.values()) {
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
