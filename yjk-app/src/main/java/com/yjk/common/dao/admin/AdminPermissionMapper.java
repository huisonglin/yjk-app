package com.yjk.common.dao.admin;

import java.util.List;
import java.util.Map;

import com.yjk.common.entity.admin.AdminPermission;
import com.yjk.manager.vo.AdminPermissionVO;


public interface AdminPermissionMapper {
	
	public List<AdminPermissionVO> selectAllMenuByStatus(int status);
	
	public List<AdminPermissionVO> selectAllMenuAndButtonByStatus(int status);
	
	public int selectPerCount(Map<String, Object> map);
	public List<AdminPermissionVO> selectPerList(Map<String, Object> map);
	
	public int updateStatusById(Map<String, Object> map);
	
    int deleteByPrimaryKey(Long perId);

    int insert(AdminPermission record);

    int insertSelective(AdminPermission record);

    AdminPermissionVO selectByPrimaryKey(Long perId);

    int updateByPrimaryKeySelective(AdminPermission record);

    int updateByPrimaryKey(AdminPermission record);
}