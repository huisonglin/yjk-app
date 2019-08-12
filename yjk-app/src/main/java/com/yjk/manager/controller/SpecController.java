package com.yjk.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yjk.common.dao.SpecMapper;
import com.yjk.common.dao.TwoStagemodelMapper;
import com.yjk.common.entity.SpecDO;
import com.yjk.common.entity.TwoStagemodelDO;
import com.yjk.manager.service.ClearCacheService;
import com.yjk.manager.service.SpecService;
import com.yjk.manager.service.TwoStagemodelService;

@Controller
@RequestMapping("spec")
public class SpecController {


	@Autowired
	SpecService specService;
	
	@RequestMapping(value="toList",produces="text/html;charset=UTF-8")
	public String toList(HttpServletRequest request,Long pid ) {
		if(pid != null) {
			request.getSession().setAttribute("specId", pid);
		}

		return "spec/list";
	}
	
	@RequestMapping(value = "/list", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String list(String page,String keyword,Long pid) {
		return specService.list(page,keyword,pid);
	}
	
	@RequestMapping("/toAdd")
	public String toAdd(HttpServletRequest request,Long twoStageModelId){
		request.setAttribute("twoStageModelId",twoStageModelId);
		return "spec/form";
	}
	
	@Autowired
	SpecMapper specMapper;
	@RequestMapping("/toEdit")
	public String toEdit(Long specId, HttpServletRequest request){
		SpecDO spec = specMapper.selectByPrimaryKey(specId);
		request.setAttribute("spec", spec);
		return "spec/form";
	}

	@Autowired
	ClearCacheService clearCacheService;
	@RequestMapping("/addOrUpdate")
	public String addOrUpdate(SpecDO specDO, RedirectAttributes redirectAttributes){

		
			int result = 0;
			if(null == specDO.getId() || specDO.getId() == 0){
				result = specMapper.insert(specDO);
			}else{
				result = specMapper.updateByPrimaryKeySelective(specDO);
			}

			//操作结果提示信息
			if(result > 0){
				clearCacheService.clearCache();
				redirectAttributes.addFlashAttribute("msg", "新增/修改操作成功！");
			}else{
				redirectAttributes.addFlashAttribute("msg", "新增/修改操作失败，请重试！");
			}
		return "redirect:/spec/toList";
	}


}
