package com.yjk.manager.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.yjk.common.entity.admin.AdminPermission;
import com.yjk.manager.common.PermissionLevel;
import com.yjk.manager.common.PermissionStatus;
import com.yjk.manager.common.PermissionType;
import com.yjk.manager.service.admin.AdminPermissionService;
import com.yjk.manager.service.admin.AdminRolePermissonService;
import com.yjk.manager.vo.admin.AdminPermissionVO;
import com.yjk.manager.vo.admin.AdminRolePermissionVO;
import com.yjk.manager.vo.admin.ZTreeVO;


@Controller
@RequestMapping("/permission")
public class PermissionController {
	
	
	@Autowired
	private AdminPermissionService permissionService;
	
	@Autowired
	private AdminRolePermissonService rolePermissonService;
	
	@RequiresPermissions(value = "per:add")
	@RequestMapping("/toAdd")
	public String toAdd(HttpServletRequest request){
		
		request.setAttribute("permissionTypeList", PermissionType.values());
		request.setAttribute("permissionStatusList", PermissionStatus.values());
		return "permission/form";
	}
	
	@RequiresPermissions(value = "per:edit")
	@RequestMapping("/toEdit")
	public String toEdit(Long perId, HttpServletRequest request){
		
		AdminPermissionVO per = permissionService.selectByPrimaryKey(perId);
		
		request.setAttribute("per", per);
		request.setAttribute("permissionTypeList", PermissionType.values());
		request.setAttribute("permissionStatusList", PermissionStatus.values());
		return "permission/form";
	}
	
	@RequiresPermissions(value={"per:add","per:edit"},logical=Logical.OR)
	@RequestMapping("/addOrUpdate")
	public String addOrUpdate(AdminPermission permission, RedirectAttributes redirectAttributes){
		Long parentId = permission.getParentId();
		if(0 == parentId){//父级选项不存在，为“顶级菜单”
			permission.setMenuLevel(PermissionLevel.TOP.getValue());
		}else{
			permission.setMenuLevel(PermissionLevel.ELSE.getValue());
		}
		
			int result = 0;
			//设置修改时间
			permission.setUpdateTime(new Date());
			if(null == permission.getPerId() || permission.getPerId() == 0){
				permission.setCreateTime(new Date());
				result = permissionService.insertSelective(permission);
			}else{
				result = permissionService.updateByPrimaryKeySelective(permission);
			}

			//操作结果提示信息
			if(result > 0){
				redirectAttributes.addFlashAttribute("msg", "新增/修改操作成功！");
			}else{
				redirectAttributes.addFlashAttribute("msg", "新增/修改操作失败，请重试！");
			}
		return "redirect:/permission/toList";
	}
	
	@RequiresPermissions(value = "per:del")
	@RequestMapping(value="/del", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String del(Long perId){
		int result = 0;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<AdminRolePermissionVO> rolePerList = rolePermissonService.selectByPerId(perId);
		if(null != rolePerList && rolePerList.size() > 0){
			resultMap.put("result", "existRoles");
			return JSON.toJSONString(resultMap);
		}
		
		result = permissionService.deleteByPrimaryKey(perId);
		
		if(result > 0){
			resultMap.put("result", "success");
		}else{
			resultMap.put("result", "fail");
		}
		
		return JSON.toJSONString(resultMap);
	}
	
	@RequiresPermissions(value = "per:list")
	@RequestMapping("/toList")
	public String toList(){
		
		return "permission/list";
		
	}
	
	@RequiresPermissions(value = "per:list")
	@RequestMapping(value="/list", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String list(String page, String perName){
		
		return permissionService.selectPerList(page, perName);
		
	}
	
	@RequiresPermissions(value = "per:status")
	@RequestMapping(value="/updateStatusById", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String updateStatusById(Integer status, Long perId){
		int result = 0;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<AdminRolePermissionVO> rolePerList = rolePermissonService.selectByPerId(perId);
		if(null != rolePerList && rolePerList.size() > 0){
			resultMap.put("result", "existRoles");
			return JSON.toJSONString(resultMap);
		}
		
		result = permissionService.updateStatusById(perId, status);
		
		if(result > 0){
			resultMap.put("result", "success");
		}else{
			resultMap.put("result", "fail");
		}
		
		return JSON.toJSONString(resultMap);
	}
	
	@RequestMapping("/toParentPermission")
	public String toParentPermission(HttpServletRequest request){
		List<AdminPermissionVO> parentList = permissionService.selectAllMenuByStatus(PermissionStatus.ON.getValue());
		List<ZTreeVO> zTreeList = dealRadioPerTree(parentList);
		
		String zTreeStr = JSONArray.fromObject(zTreeList).toString();
		//System.out.println(zTreeStr);
		
		request.setAttribute("zTreeStr", zTreeStr);
		return "permission/parent";
	}

	//获取父级菜单单选树
	private List<ZTreeVO> dealRadioPerTree(List<AdminPermissionVO> parentList) {
		List<ZTreeVO> zTreeList = new ArrayList<ZTreeVO>();
		ZTreeVO zTree = null;
		
		for (AdminPermissionVO per : parentList) {
			zTree = new ZTreeVO();
			zTree.setId(per.getPerId());
			zTree.setName(per.getPerName());
			zTree.setpId(per.getParentId());
			zTree.setOpen(true);
			
			zTreeList.add(zTree);
		}
		return zTreeList;
	}
}
