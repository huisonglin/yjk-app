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
import com.yjk.common.entity.admin.AdminRole;
import com.yjk.manager.common.PermissionStatus;
import com.yjk.manager.common.RoleStatus;
import com.yjk.manager.service.admin.AdminPermissionService;
import com.yjk.manager.service.admin.AdminRolePermissonService;
import com.yjk.manager.service.admin.AdminRoleService;
import com.yjk.manager.service.admin.AdminUserRoleService;
import com.yjk.manager.vo.admin.AdminPermissionVO;
import com.yjk.manager.vo.admin.AdminRolePermissionVO;
import com.yjk.manager.vo.admin.AdminRoleVO;
import com.yjk.manager.vo.admin.AdminUserRoleVO;
import com.yjk.manager.vo.admin.ZTreeVO;


@Controller
@RequestMapping("/role")
public class RoleController {
	
	
	@Autowired
	private AdminRoleService roleService;
	
	@Autowired
	private AdminPermissionService permissionService;
	
	@Autowired
	private AdminUserRoleService userRoleService;
	
	@Autowired
	private AdminRolePermissonService rolePermissonService;
	
	private String getAllPermission(Long roleId){
		List<AdminPermissionVO> perList = permissionService.selectAllMenuAndButtonByStatus(PermissionStatus.ON.getValue());
		List<AdminRolePermissionVO> roleList = new ArrayList<AdminRolePermissionVO>();
		
		if(null != roleId || "0".equals(roleId)){
			roleList = rolePermissonService.selectByRoleId(roleId);
		}
		
		
		List<ZTreeVO> zTreeList = new ArrayList<ZTreeVO>();
		ZTreeVO zTree = null;
		for (AdminPermissionVO per : perList) {
			zTree = new ZTreeVO();
			
			zTree.setId(per.getPerId());
			zTree.setName(per.getPerName());
			zTree.setpId(per.getParentId());
			zTree.setOpen(true);
			
			for (AdminRolePermissionVO rolePer : roleList) {
				if(per.getPerId() == rolePer.getPerId()){
					zTree.setChecked(true);
					continue;
				}
			}
			
			zTreeList.add(zTree);
		}
		
		return JSONArray.fromObject(zTreeList).toString();
	}
	
	@RequiresPermissions(value = "role:add")
	@RequestMapping("/toAdd")
	public String toAdd(HttpServletRequest request){
		
		String zTreeStr = getAllPermission(null);
		
		request.setAttribute("zTreeStr", zTreeStr);
		request.setAttribute("roleStatusList", RoleStatus.values());
		return "role/form";
	}
	
	@RequiresPermissions(value = "role:edit")
	@RequestMapping("/toEdit")
	public String toEdit(Long roleId, HttpServletRequest request){
		AdminRoleVO role = roleService.selectByPrimaryKey(roleId);
        String zTreeStr = getAllPermission(roleId);
		
        request.setAttribute("role", role);
		request.setAttribute("zTreeStr", zTreeStr);
		request.setAttribute("roleStatusList", RoleStatus.values());
		return "role/form";
	}
	
	@RequiresPermissions(value={"role:add","role:edit"},logical=Logical.OR)
	@RequestMapping("/addOrUpdate")
	public String addOrUpdate(AdminRole role, String rolePersStr, RedirectAttributes redirectAttributes){
			int result = 0;
			//设置修改时间
			role.setUpdateTime(new Date());
			if(null == role.getRoleId() || role.getRoleId() == 0){
				role.setCreateTime(new Date());
				result = roleService.insertSelective(role, rolePersStr);
			}else{
				result = roleService.updateByPrimaryKeySelective(role, rolePersStr);
			}

			//操作结果提示信息
			if(result > 0){
				redirectAttributes.addFlashAttribute("msg", "新增/修改操作成功！");
			}else{
				redirectAttributes.addFlashAttribute("msg", "新增/修改操作失败，请重试！");
			}
		return "redirect:/role/toList";
	}
	
	@RequiresPermissions(value = "role:del")
	@RequestMapping(value="/del", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String del(Long roleId){
		int result = 0;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<AdminUserRoleVO> userRoleList = userRoleService.selectByRoleId(roleId);
		
		if(null != userRoleList && userRoleList.size() > 0){
			resultMap.put("result", "existUsers");
			return JSON.toJSONString(resultMap);
		}
		
		result = roleService.deleteByPrimaryKey(roleId);
		
		if(result > 0){
			resultMap.put("result", "success");
		}else{
			resultMap.put("result", "fail");
		}
		
		return JSON.toJSONString(resultMap);
		
	}
	
	@RequiresPermissions(value = "role:list")
	@RequestMapping("/toList")
	public String toList(){
		
		return "role/list";
		
	}
	
	@RequiresPermissions(value = "role:list")
	@RequestMapping(value="/list", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String list(String page, String roleName){
		
		return roleService.selectRolesList(page, roleName);
		
	}
	
	@RequiresPermissions(value = "role:status")
	@RequestMapping(value="/updateStatusById", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String updateStatusById(Integer status, Long roleId){
		int result = 0;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
        List<AdminUserRoleVO> userRoleList = userRoleService.selectByRoleId(roleId);
		
		if(null != userRoleList && userRoleList.size() > 0){
			resultMap.put("result", "existUsers");
			return JSON.toJSONString(resultMap);
		}
		
		result = roleService.updateStatusById(roleId, status);
		
		if(result > 0){
			resultMap.put("result", "success");
		}else{
			resultMap.put("result", "fail");
		}
		
		return JSON.toJSONString(resultMap);
	}

}
