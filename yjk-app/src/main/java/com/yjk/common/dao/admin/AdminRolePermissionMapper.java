package com.yjk.common.dao.admin;

import java.util.List;

import com.yjk.common.entity.admin.AdminRolePermission;
import com.yjk.manager.vo.AdminRolePermissionVO;


public interface AdminRolePermissionMapper {
    
	public List<AdminRolePermissionVO> selectByRoleId(Long roleId);
	
	public List<AdminRolePermissionVO> selectByPerId(Long perId);
	
	public int deleteByRoleId(Long roleId);
	
	public int insertBatch(List<AdminRolePermission> list);
	
}