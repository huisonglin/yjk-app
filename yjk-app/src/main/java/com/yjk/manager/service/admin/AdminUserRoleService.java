package com.yjk.manager.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjk.common.dao.admin.AdminUserRoleMapper;
import com.yjk.manager.vo.admin.AdminUserRoleVO;


@Service
public class AdminUserRoleService {

	
	@Autowired
	private AdminUserRoleMapper adminUserRoleMapper;
	
	public List<AdminUserRoleVO> selectByUserId(Long userId){
		List<AdminUserRoleVO> userRoleList = null;
		userRoleList = adminUserRoleMapper.selectByUserId(userId);
		return userRoleList;
	}
	
	public List<AdminUserRoleVO> selectByRoleId(Long roleId){
		List<AdminUserRoleVO> userRoleList = null;
			userRoleList = adminUserRoleMapper.selectByRoleId(roleId);
		return userRoleList;
	}
}
