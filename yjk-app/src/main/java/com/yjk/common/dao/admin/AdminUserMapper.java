package com.yjk.common.dao.admin;

import java.util.List;
import java.util.Map;

import com.yjk.common.entity.admin.AdminUser;
import com.yjk.manager.vo.admin.AdminUserVO;
import com.yjk.manager.vo.admin.UserPermissionMenuVO;
import com.yjk.manager.vo.admin.UserPermissionVO;


public interface AdminUserMapper {
	
	public List<UserPermissionMenuVO> getFirstMenuByUserId(Long userId);
	
	public List<UserPermissionMenuVO> getElseMenuByUserId(Map<String, Object> map);
	
	public List<UserPermissionVO> selectPermissionByUserId(Long userId);
	
	public int getUsersCount(Map<String, Object> map);
	public List<AdminUserVO> getUsersList(Map<String, Object> map);
	
	public AdminUserVO getUserByLoginName(String loginName);
	
	public int updateStatusById(Map<String, Object> map);
	
	public int updatePasswordById(Map<String, Object> map);
	
    int deleteByPrimaryKey(Long userId);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUserVO selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
}