package com.yjk.manager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yjk.common.dao.admin.AdminRoleMapper;
import com.yjk.common.dao.admin.AdminRolePermissionMapper;
import com.yjk.common.entity.admin.AdminRole;
import com.yjk.common.entity.admin.AdminRolePermission;
import com.yjk.manager.common.Constants;
import com.yjk.manager.utils.Page;
import com.yjk.manager.utils.PageUtil;
import com.yjk.manager.vo.AdminRoleVO;

@Service
public class AdminRoleService {

	
	@Autowired
	private AdminRoleMapper adminRoleMapper;
	
	@Autowired
	private AdminRolePermissionMapper adminRolePermissionMapper;
	
	//role tree
	public List<AdminRoleVO> selectAllRolesByStatus(int status){
		List<AdminRoleVO> roleList = null;
		roleList = adminRoleMapper.selectAllRolesByStatus(status);
		return roleList;
	}
	
	//page
	public String selectRolesList(String page, String roleName) {
		JSONObject result = null;
		
			int intPage = Integer.parseInt((page == null || "0".equals(page)) ? "1" : page);
            int pageSize = Constants.PAGE_SIZE;

            int pageStart = (intPage - 1) * pageSize;
            int pageEnd = pageSize;
            
			Map<String, Object> map = new HashMap<String, Object>();
			if(StringUtils.isNotBlank(roleName)){
				map.put("roleName", roleName);
			}
		    map.put("pageStart", pageStart);
			map.put("pageEnd", pageEnd);
			
			List<AdminRoleVO> roleList = adminRoleMapper.selectRolesList(map);
			
            Integer roleCount = adminRoleMapper.selectRolesCount(map);
            Page pageObj = PageUtil.createPage(pageSize, roleCount, intPage);
            
            Map<String, Object> jsonMap = new HashMap<String, Object>();
	            jsonMap.put("pageCount", pageObj.getTotalPage() == 0 ? 1 : pageObj.getTotalPage());//�����ҳ������
	            jsonMap.put("result", roleList);
            result = JSONObject.fromObject(jsonMap);
		return result.toString();
	}
	
	public int updateStatusById(Long roleId, int newStatus){
		int result = 0;
			Map<String, Object> map = new HashMap<String, Object>();
				map.put("roleId", roleId);
				map.put("status", newStatus);
			result = adminRoleMapper.updateStatusById(map);
		return result;
	}
	
	@Transactional
	public int deleteByPrimaryKey(Long roleId){
		int result = 0;
			result = adminRolePermissionMapper.deleteByRoleId(roleId);
			if(result > 0){
				result = adminRoleMapper.deleteByPrimaryKey(roleId);
				if(result <= 0){
					try {
						throw new RuntimeException();
					} catch (Exception e) {
						//do nothing
					}
				}
			}
		return result;
	}
	
	@Transactional
	public int insertSelective(AdminRole record, String persStr){
		int result = 0;
			result = adminRoleMapper.insertSelective(record);
			
			if(result > 0){
				String [] pers = persStr.split("\\;");
				
				List<AdminRolePermission> rolePerList = new ArrayList<AdminRolePermission>();
				AdminRolePermission rolePer = null;
				Date now = new Date();
				for (String per : pers) {
					rolePer = new AdminRolePermission();
						rolePer.setRoleId(record.getRoleId());
						rolePer.setPerId(Long.parseLong(per));
						rolePer.setCreateTime(now);
						rolePer.setUpdateTime(now);
					rolePerList.add(rolePer);
				}
				
				result = adminRolePermissionMapper.insertBatch(rolePerList);
				if(result <= 0){
					try {
						throw new RuntimeException();
					} catch (Exception e) {
						//do nothing
					}
				}
			}
		return result;
	}
	
	public AdminRoleVO selectByPrimaryKey(Long roleId){
		AdminRoleVO role = null;
			role = adminRoleMapper.selectByPrimaryKey(roleId);
		return role;
	}
	
	@Transactional
	public int updateByPrimaryKeySelective(AdminRole record, String persStr){
		int result = 0;
			result = adminRoleMapper.updateByPrimaryKeySelective(record);
			if(result > 0){
				result = adminRolePermissionMapper.deleteByRoleId(record.getRoleId());
				if(result > 0){
					String [] pers = persStr.split("\\;");
					
					List<AdminRolePermission> rolePerList = new ArrayList<AdminRolePermission>();
					AdminRolePermission rolePer = null;
					Date now = new Date();
					for (String per : pers) {
						rolePer = new AdminRolePermission();
							rolePer.setRoleId(record.getRoleId());
							rolePer.setPerId(Long.parseLong(per));
							rolePer.setCreateTime(now);
							rolePer.setUpdateTime(now);
						rolePerList.add(rolePer);
					}
					
					result = adminRolePermissionMapper.insertBatch(rolePerList);
					if(result <= 0){
						try {
							throw new RuntimeException();
						} catch (Exception e) {
							//do nothing
						}
					}
				}else{
					try {
						throw new RuntimeException();
					} catch (Exception e) {
						//do nothing
					}
				}
			}
		return result;
	}
}
