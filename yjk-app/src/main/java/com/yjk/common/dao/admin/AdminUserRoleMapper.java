package com.yjk.common.dao.admin;

import java.util.List;

import com.yjk.common.entity.admin.AdminUserRole;
import com.yjk.manager.vo.AdminUserRoleVO;


public interface AdminUserRoleMapper {
    
	public List<AdminUserRoleVO> selectByUserId(Long userId);
	
	public List<AdminUserRoleVO> selectByRoleId(Long roleId);
	
	public int delByUserId(Long userId);
	
	public int insertBatch(List<AdminUserRole> list);
	
}