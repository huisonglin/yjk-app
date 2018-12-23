package com.yjk.manager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yjk.common.dao.admin.AdminUserMapper;
import com.yjk.common.dao.admin.AdminUserRoleMapper;
import com.yjk.common.entity.admin.AdminUser;
import com.yjk.common.entity.admin.AdminUserRole;
import com.yjk.manager.common.Constants;
import com.yjk.manager.utils.Page;
import com.yjk.manager.utils.PageUtil;
import com.yjk.manager.vo.AdminUserVO;
import com.yjk.manager.vo.UserPermissionMenuVO;
import com.yjk.manager.vo.UserPermissionVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;



@Service
public class AdminUserService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AdminUserMapper adminUserMapper;
	
	@Autowired
	private AdminUserRoleMapper adminUserRoleMapper;
	
	//first menu
	public List<UserPermissionMenuVO> getFirstMenuByUserId(Long userId){
		List<UserPermissionMenuVO> userFirstMenuList = null;
		try{
			userFirstMenuList = adminUserMapper.getFirstMenuByUserId(userId);
		}catch(Exception e){
			logger.error("call:AdminUserService.getFirstMenuByUserId(...) exception:::" + e);
		}
		return userFirstMenuList;
	}
	
	//else menu
	public List<UserPermissionMenuVO> getElseMenuByUserId(Long userId, Long perParentId){
		List<UserPermissionMenuVO> userElseMenuList = null;
		try{
			Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", userId);
				map.put("perParentId", perParentId);
			
				userElseMenuList = adminUserMapper.getElseMenuByUserId(map);
		}catch(Exception e){
			logger.error("call:AdminUserService.getElseMenuByUserId(...) exception:::" + e);
		}
		return userElseMenuList;
	}
	
	//user per
	public List<UserPermissionVO> selectPermissionByUserId(Long userId){
		List<UserPermissionVO> userPermissionList = null;
		try{
			userPermissionList = adminUserMapper.selectPermissionByUserId(userId);
		}catch (Exception e) {
            logger.error("call:AdminUserService.selectPermissionByUserId(...) exception:::" + e);
        }
		return userPermissionList;
	}
	
	//page
	public String getUsersList(String page, String nickName) {
		JSONObject result = null;
		
		try {
			int intPage = Integer.parseInt((page == null || "0".equals(page)) ? "1" : page);
            int pageSize = Constants.PAGE_SIZE;

            int pageStart = (intPage - 1) * pageSize;
            int pageEnd = pageSize;
            
			Map<String, Object> map = new HashMap<String, Object>();
			if(StringUtils.isNotBlank(nickName)){
				map.put("nickName", nickName);
			}
		    map.put("pageStart", pageStart);
			map.put("pageEnd", pageEnd);
			
			List<AdminUserVO> usersList = adminUserMapper.getUsersList(map);
			
            Integer usersCount = adminUserMapper.getUsersCount(map);
            Page pageObj = PageUtil.createPage(pageSize, usersCount, intPage);
            
            Map<String, Object> jsonMap = new HashMap<String, Object>();
	            jsonMap.put("pageCount", pageObj.getTotalPage() == 0 ? 1 : pageObj.getTotalPage());//�����ҳ������
	            jsonMap.put("result", usersList);
            result = JSONObject.fromObject(jsonMap);
		}catch (Exception e) {
            logger.error("call:AdminUserService.getUsersList(...) exception:::" + e);
        }
		return result.toString();
	}
	
	//login
	public AdminUserVO getUserByLoginName(String loginName){
		AdminUserVO adminUser = null;
		try{
			adminUser = adminUserMapper.getUserByLoginName(loginName);
		}catch (Exception e) {
            e.printStackTrace();
        }
		return adminUser;
	}
	
	public int updateStatusById(Long userId, Integer status) {
		Integer result = 0;
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("status", status);
			
			result = adminUserMapper.updateStatusById(map);
		}catch (Exception e) {
            logger.error("call:AdminUserService.updateUserStatusById(...) exception:::" + e);
        }
		return result;
	}
	
	public Integer updatePasswordById(Long userId, String password) {
		Integer result = 0;
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("password", password);
			
			result = adminUserMapper.updatePasswordById(map);
		}catch (Exception e) {
            logger.error("call:AdminUserService.updatePasswordById(...) exception:::" + e);
        }
		return result;
	}
	
	@Transactional
	public int deleteByPrimaryKey(Long userId){
		int result = 0;
		try{
			result = adminUserRoleMapper.delByUserId(userId);
			if(result > 0){
				result = adminUserMapper.deleteByPrimaryKey(userId);
				if(result <= 0){
					try {
						throw new RuntimeException();
					} catch (Exception e) {
						//do nothing
					}
				}
			}
		}catch (Exception e) {
            logger.error("call:AdminUserService.deleteByPrimaryKey(...) exception:::" + e);
        }
		return result;
	}
	
	@Transactional
	public int insertSelective(AdminUser record, String rolesStr){
		int result = 0;
		try{
			result = adminUserMapper.insertSelective(record);
			if(result > 0){
				String [] roles = rolesStr.split("\\;");
				
				List<AdminUserRole> userRoleList = new ArrayList<AdminUserRole>();
				AdminUserRole userRole = null;
				
				Date now = new Date();
				for (String role : roles) {
					userRole = new AdminUserRole();
						userRole.setUserId(record.getUserId());
						userRole.setRoleId(Long.parseLong(role));
						userRole.setCreateTime(now);
						userRole.setUpdateTime(now);
					userRoleList.add(userRole);
				}
				
				result = adminUserRoleMapper.insertBatch(userRoleList);
				if(result <= 0){
					try {
						throw new RuntimeException();
					} catch (Exception e) {
						//do nothing
					}
				}
			}
		}catch (Exception e) {
            logger.error("call:AdminUserService.insertSelective(...) exception:::" + e);
        }
		return result;
	}
	
	public AdminUserVO selectByPrimaryKey(Long userId){
		AdminUserVO adminUser = null;
		try{
			adminUser = adminUserMapper.selectByPrimaryKey(userId);
		}catch (Exception e) {
            logger.error("call:AdminUserService.selectByPrimaryKey(...) exception:::" + e);
        }
		return adminUser;
	}
	
	@Transactional
	public int updateByPrimaryKeySelective(AdminUser record, String rolesStr){
		int result = 0;
		try{
			result = adminUserMapper.updateByPrimaryKey(record);
			if(result > 0){
				result = adminUserRoleMapper.delByUserId(record.getUserId());
				if(result > 0){
					String [] roles = rolesStr.split("\\;");
					
					List<AdminUserRole> userRoleList = new ArrayList<AdminUserRole>();
					AdminUserRole userRole = null;
					
					Date now = new Date();
					for (String role : roles) {
						userRole = new AdminUserRole();
							userRole.setUserId(record.getUserId());
							userRole.setRoleId(Long.parseLong(role));
							userRole.setCreateTime(now);
							userRole.setUpdateTime(now);
						userRoleList.add(userRole);
					}
					
					result = adminUserRoleMapper.insertBatch(userRoleList);
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
		}catch (Exception e) {
            logger.error("call:AdminUserService.updateByPrimaryKeySelective(...) exception:::" + e);
        }
		return result;
	}
}
