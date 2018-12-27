package com.yjk.manager.vo.admin;

/******
 * 某一用户所具有的权限VO
 * @author ATH
 *
 */
public class UserPermissionVO {
	
	private String perName;
	
    private String permission;
    
    private String url;

	public String getPerName() {
		return perName;
	}

	public void setPerName(String perName) {
		this.perName = perName;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
