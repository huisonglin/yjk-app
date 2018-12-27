package com.yjk.manager.service.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjk.common.dao.admin.AdminPermissionMapper;
import com.yjk.common.entity.admin.AdminPermission;
import com.yjk.manager.common.Constants;
import com.yjk.manager.utils.Page;
import com.yjk.manager.utils.PageUtil;
import com.yjk.manager.vo.admin.AdminPermissionVO;

@Service
public class AdminPermissionService {

	
	@Autowired
	private AdminPermissionMapper adminPermissionMapper;
	
	//parent menu
	public List<AdminPermissionVO> selectAllMenuByStatus(int status){
		List<AdminPermissionVO> adminPerList = null;
			adminPerList = adminPermissionMapper.selectAllMenuByStatus(status);
		return adminPerList;
	}
	
	//per tree
	public List<AdminPermissionVO> selectAllMenuAndButtonByStatus(int status){
		List<AdminPermissionVO> adminPerList = null;
			adminPerList = adminPermissionMapper.selectAllMenuAndButtonByStatus(status);
		return adminPerList;
	}
	
	//page
	public String selectPerList(String page, String perName ){
		JSONObject result = null;
		
			int intPage = Integer.parseInt((page == null || "0".equals(page)) ? "1" : page);
            int pageSize = Constants.PAGE_SIZE;

            int pageStart = (intPage - 1) * pageSize;
            int pageEnd = pageSize;
            
			Map<String, Object> map = new HashMap<String, Object>();
			if(StringUtils.isNotBlank(perName)){
				map.put("perName", perName);
			}
		    map.put("pageStart", pageStart);
			map.put("pageEnd", pageEnd);
			
			List<AdminPermissionVO> perList = adminPermissionMapper.selectPerList(map);
			
            Integer perCount = adminPermissionMapper.selectPerCount(map);
            Page pageObj = PageUtil.createPage(pageSize, perCount, intPage);
            
            Map<String, Object> jsonMap = new HashMap<String, Object>();
	            jsonMap.put("pageCount", pageObj.getTotalPage() == 0 ? 1 : pageObj.getTotalPage());
	            jsonMap.put("result", perList);
            result = JSONObject.fromObject(jsonMap);
		return result.toString();
	}
	
	//update status
	public int updateStatusById(Long perId, int newStatus){
		int result = 0;
			Map<String, Object> map = new HashMap<String, Object>();
				map.put("perId", perId);
				map.put("status", newStatus);
			result = adminPermissionMapper.updateStatusById(map);
		return result;
	}
	
	//del
	public int deleteByPrimaryKey(Long perId){
		int result = 0;
			result = adminPermissionMapper.deleteByPrimaryKey(perId);
		return result;
	}
	
	//add
	public int insertSelective(AdminPermission record){
		int result = 0;
			result = adminPermissionMapper.insertSelective(record);
		return result;
	}
	
	//select obj
	public AdminPermissionVO selectByPrimaryKey(Long perId){
		AdminPermissionVO adminPer = null;
			adminPer = adminPermissionMapper.selectByPrimaryKey(perId);
		return adminPer;
	}
	
	//update
	public int updateByPrimaryKeySelective(AdminPermission record){
		int result = 0;
			result = adminPermissionMapper.updateByPrimaryKeySelective(record);
		return result;
	}
}
