package com.yjk.manager.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yjk.common.dao.ModelMapper;
import com.yjk.common.dao.TwoStagemodelMapper;
import com.yjk.common.entity.ModelDO;
import com.yjk.common.entity.TwoStagemodelDO;
import com.yjk.common.entity.admin.AdminPermission;
import com.yjk.manager.common.PermissionLevel;
import com.yjk.manager.common.RoleStatus;
import com.yjk.manager.service.ClearCacheService;
import com.yjk.manager.service.ModelService;
import com.yjk.manager.service.TwoStagemodelService;
import com.yjk.manager.vo.admin.AdminRoleVO;

@Controller
@RequestMapping("/twoStageModel")
public class TwoStagemodelController {

	@Autowired
	TwoStagemodelService twoStagemodelService;
	
	@RequestMapping(value="toList",produces="text/html;charset=UTF-8")
	public String toList(HttpServletRequest request,Long pid ) {
		if(pid != null) {
			request.getSession().setAttribute("pid", pid);
		}
		return "twoStageModel/list";
	}
	
	@RequestMapping(value = "/list", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String list(String page,String keyword,Long pid) {
		return twoStagemodelService.list(page,keyword,pid);
	}
	
	@RequestMapping("/toAdd")
	public String toAdd(HttpServletRequest request,Long modelId){
		request.setAttribute("modelId", modelId);
		return "twoStageModel/form";
	}
	
	@Autowired
	TwoStagemodelMapper twoStagemodelMapper;
	@RequestMapping("/toEdit")
	public String toEdit(Long twoStageModelId, HttpServletRequest request){
		TwoStagemodelDO model = twoStagemodelMapper.selectByPrimaryKey(twoStageModelId);
		request.setAttribute("twoStageModel", model);
		return "twoStageModel/form";
	}

	@Autowired
	ClearCacheService clearCacheService;
	@RequestMapping("/addOrUpdate")
	public String addOrUpdate(TwoStagemodelDO model, RedirectAttributes redirectAttributes){

		
			int result = 0;
			if(null == model.getId() || model.getId() == 0){
				result = twoStagemodelMapper.insert(model);
			}else{
				result = twoStagemodelMapper.updateByPrimaryKeySelective(model);
			}

			//操作结果提示信息
			if(result > 0){
				redirectAttributes.addFlashAttribute("msg", "新增/修改操作成功！");
				clearCacheService.clearCache();
			}else{
				redirectAttributes.addFlashAttribute("msg", "新增/修改操作失败，请重试！");
			}
		return "redirect:/twoStageModel/toList";
	}
}
