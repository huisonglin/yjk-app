package com.yjk.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjk.common.dao.admin.AdminRolePermissionMapper;
import com.yjk.manager.vo.AdminRolePermissionVO;

@Service
public class AdminRolePermissonService {

	
	@Autowired
	private AdminRolePermissionMapper adminRolePermissionMapper;
	
	public List<AdminRolePermissionVO> selectByRoleId(Long roleId){
		List<AdminRolePermissionVO> rolePerList = null;
			rolePerList = adminRolePermissionMapper.selectByRoleId(roleId);
		return rolePerList;
	}
	
	public List<AdminRolePermissionVO> selectByPerId(Long perId){
		List<AdminRolePermissionVO> rolePerList = null;
			rolePerList = adminRolePermissionMapper.selectByPerId(perId);
		return rolePerList;
	}
	
}
