package com.yjk.manager.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.yjk.common.entity.admin.AdminUser;
import com.yjk.manager.common.Constants;
import com.yjk.manager.common.RoleStatus;
import com.yjk.manager.common.UserStatus;
import com.yjk.manager.service.AdminRoleService;
import com.yjk.manager.service.AdminUserRoleService;
import com.yjk.manager.service.AdminUserService;
import com.yjk.manager.shiro.ShiroDbRealm.ShiroUser;
import com.yjk.manager.utils.MD5;
import com.yjk.manager.vo.AdminRoleVO;
import com.yjk.manager.vo.AdminUserRoleVO;
import com.yjk.manager.vo.AdminUserVO;
import com.yjk.manager.vo.ZTreeVO;


@Controller
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private AdminUserService userService;
	
	@Autowired
	private AdminRoleService roleService;
	
	@Autowired
	private AdminUserRoleService userRoleService;
	
	private String getAllRoles(	Long userId){
		List<AdminRoleVO> roleList = roleService.selectAllRolesByStatus(RoleStatus.ON.getValue());
		List<AdminUserRoleVO> userRoleList = new ArrayList<AdminUserRoleVO>();
		
		if(null != userId || "0".equals(userId)){
			userRoleList = userRoleService.selectByUserId(userId);
		}
		
		List<ZTreeVO> zTreeList = new ArrayList<ZTreeVO>();
		ZTreeVO zTree = null;
		for (AdminRoleVO role : roleList) {
			zTree = new ZTreeVO();
			
			zTree.setId(role.getRoleId());
			zTree.setName(role.getRoleName());
			zTree.setpId(0L);
			zTree.setOpen(true);
			
			for (AdminUserRoleVO userRole : userRoleList) {
				if(role.getRoleId() == userRole.getRoleId()){
					zTree.setChecked(true);
					continue;
				}
			}
			
			zTreeList.add(zTree);
		}
		
		return JSONArray.fromObject(zTreeList).toString();
	}
	
	@RequiresPermissions(value = "user:add")
	@RequestMapping("/toAdd")
	public String toAdd(HttpServletRequest request){
		
		String zTreeStr = getAllRoles(null);
		
		request.setAttribute("zTreeStr", zTreeStr);
		request.setAttribute("userStatusList", UserStatus.values());
		return "user/form";
	}
	
	@RequiresPermissions(value = "user:edit")
	@RequestMapping("/toEdit")
	public String toEdit(Long userId, HttpServletRequest request){
		AdminUserVO user = userService.selectByPrimaryKey(userId);
		String zTreeStr = getAllRoles(userId);
		
		request.setAttribute("user", user);
		request.setAttribute("zTreeStr", zTreeStr);
		request.setAttribute("userStatusList", UserStatus.values());
		return "user/form";
	}
	
	@RequiresPermissions(value={"user:add","user:edit"},logical=Logical.OR) 
	@RequestMapping("/addOrUpdate")
	public String addOrUpdate(AdminUser user, String userRolesStr, RedirectAttributes redirectAttributes){
			int result = 0;
			//设置修改时间
			user.setUpdateTime(new Date());
			if(null == user.getUserId() || user.getUserId() == 0){
				user.setCreateTime(new Date());
				user.setPassword(new MD5().getMD5ofStr(user.getPassword()));
				result = userService.insertSelective(user, userRolesStr);
			}else{
				result = userService.updateByPrimaryKeySelective(user, userRolesStr);
			}

			//操作结果提示信息
			if(result > 0){
				redirectAttributes.addFlashAttribute("msg", "新增/修改操作成功！");
			}else{
				redirectAttributes.addFlashAttribute("msg", "新增/修改操作失败，请重试！");
			}
		
		return "redirect:/user/toList";
	}
	
	@RequiresPermissions(value = "user:del")
	@RequestMapping(value="/del", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String del(Long userId){
		int result = 0;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		result = userService.deleteByPrimaryKey(userId);
		
		if(result > 0){
			resultMap.put("result", "success");
		}else{
			resultMap.put("result", "fail");
		}
		
		return JSON.toJSONString(resultMap);
	}
	
	@RequiresPermissions(value = "user:list")
	@RequestMapping("/toList")
	public String toList(HttpServletRequest request){
		ShiroUser loginUser = this.getCurrentUserInfo();
		
		request.setAttribute("loginUser", loginUser);
		return "user/list";
		
	}
	
	
	@RequiresPermissions(value = "user:list")
	@RequestMapping(value="/list", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String list(String page, String nickName){
		
		return userService.getUsersList(page, nickName);
		
	}
	
	@RequiresPermissions(value = "user:status")
	@RequestMapping(value="/updateStatusById", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String updateStatusById(Integer status, Long userId){
		int result = 0;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		result = userService.updateStatusById(userId, status);
		
		if(result > 0){
			resultMap.put("result", "success");
		}else{
			resultMap.put("result", "fail");
		}
		
		return JSON.toJSONString(resultMap);
	}

	@RequiresPermissions(value = "user:init")
	@RequestMapping(value="/resetPasswordById", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String resetPasswordById(Long userId){
		int result = 0;
		result = userService.updatePasswordById(userId, new MD5().getMD5ofStr(Constants.INIT_PASSWORD));
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(result > 0){
			resultMap.put("result", "success");
		}else{
			resultMap.put("result", "fail");
		}
		
		return JSON.toJSONString(resultMap);
	}
	
	//登录名是否已注册
	@RequestMapping(value="/loginNameIsUsed", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String loginNameIsUsed(String loginName){
		//bug
		if(StringUtils.isNotBlank(loginName)){
			loginName = loginName.substring(0, loginName.length() - 1);
		}
		
		AdminUserVO user = userService.getUserByLoginName(loginName);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(null != user && user.getUserId() > 0){
			resultMap.put("result", "isUsed");
		}else{
			resultMap.put("result", "isNotUsed");
		}
		
		return JSON.toJSONString(resultMap);
	}
	
	/******
	 * 修改密码
	 * @return
	 */
	@RequestMapping(value="/toChangePasswordPage")
	public String toChangePasswordPage(){
		
		return "user/changePassword";
	}
	
	/******
	 * 修改密码
	 * @param oldPassword
	 * @param newPassword
	 * @param request
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/changePasswordByUserId")
	public String changePasswordByUserId(String oldPassword, String newPassword, HttpServletRequest request, RedirectAttributes redirectAttributes){
		//获取登录用户信息
		ShiroUser shiroUser =  this.getCurrentUserInfo();
		Long loginId = shiroUser.id;
		
		AdminUserVO user = userService.selectByPrimaryKey(loginId);
		String oldPasswordMd5 = user.getPassword();
		
		Integer result = 0;
		if(!oldPasswordMd5.equals(new MD5().getMD5ofStr(oldPassword))){
			redirectAttributes.addFlashAttribute("msg", "原密码输入不正确，请确认！");
		}else{
			String newPasswordMd5 = new MD5().getMD5ofStr(newPassword);
			result = userService.updatePasswordById(loginId, newPasswordMd5);
			if(result > 0){
				redirectAttributes.addFlashAttribute("msg", "密码修改成功！");
			}else{
				redirectAttributes.addFlashAttribute("msg", "密码修改失败，请重试！");
			}
		}
				
		return "redirect:/user/toChangePasswordPage";
	}
	
	/**
     * 取出Shiro中的当前用户
     */
    private ShiroUser getCurrentUserInfo() {
    	ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user;
    }
}
