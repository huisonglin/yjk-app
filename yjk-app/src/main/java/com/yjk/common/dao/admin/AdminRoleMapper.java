package com.yjk.common.dao.admin;

import java.util.List;
import java.util.Map;

import com.yjk.common.entity.admin.AdminRole;
import com.yjk.manager.vo.AdminRoleVO;

public interface AdminRoleMapper {
	
	public List<AdminRoleVO> selectAllRolesByStatus(int status);
	
	public int selectRolesCount(Map<String, Object> map);
	public List<AdminRoleVO> selectRolesList(Map<String, Object> map);
	
	public int updateStatusById(Map<String, Object> map);
	
    int deleteByPrimaryKey(Long roleId);

    int insert(AdminRole record);

    int insertSelective(AdminRole record);

    AdminRoleVO selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(AdminRole record);

    int updateByPrimaryKey(AdminRole record);
}