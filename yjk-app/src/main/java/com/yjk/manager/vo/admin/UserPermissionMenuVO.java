package com.yjk.manager.vo.admin;

import java.util.ArrayList;
import java.util.List;

/******
 * 某一用户所具有的权限菜单VO
 * @author ATH
 *
 */
public class UserPermissionMenuVO {
	
	private Long perId;
	
	private String perName;
	
    private String permission;
    
    private String url;
    
    private Long parentId;
    
    private String parentPerName;
    
    private List<UserPermissionMenuVO> childs = new ArrayList<UserPermissionMenuVO>();

	public Long getPerId() {
		return perId;
	}

	public void setPerId(Long perId) {
		this.perId = perId;
	}

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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentPerName() {
		return parentPerName;
	}

	public void setParentPerName(String parentPerName) {
		this.parentPerName = parentPerName;
	}

	public List<UserPermissionMenuVO> getChilds() {
		return childs;
	}

	public void setChilds(List<UserPermissionMenuVO> childs) {
		this.childs = childs;
	}
}
