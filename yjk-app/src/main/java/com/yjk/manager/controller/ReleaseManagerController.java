package com.yjk.manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yjk.app.service.DeviceRentOutInfoService;
import com.yjk.app.service.DeviceRentalInNeedInfoService;
import com.yjk.manager.controller.admin.BaseController;
import com.yjk.manager.dto.SearchReleaseDTO;
import com.yjk.manager.service.ReleaseManagerService;

@Controller
@RequestMapping("/release")
public class ReleaseManagerController extends BaseController{

	@Autowired
	ReleaseManagerService releaseManagerService;
	
	@Autowired
	DeviceRentOutInfoService deviceRentOutInfoService;
	
	@Autowired
	DeviceRentalInNeedInfoService deviceRentalInNeedInfoService;
	
	@RequiresPermissions(value = "release:list")
	@RequestMapping(value = "/toList", produces="text/html;charset=UTF-8")
	public String toList() {
		return "release/list";
	}
	
	@ResponseBody
	@RequiresPermissions(value = "release:list")
	@RequestMapping(value = "/list", produces="text/html;charset=UTF-8")
	public String list(String page,SearchReleaseDTO dto) {
		return releaseManagerService.releaseList(page,dto);
	}
	
	/**
	 * 
	 * @param id
	 * @param type 1出租 2求租
	 * @param status 1未上架 2已上架
	 * @throws Exception 
	 */
	@ResponseBody
	@RequiresPermissions(value = "release:status")
	@RequestMapping("changeStatus")
	public String changeStatus(Long id,Integer type,Integer status) throws Exception{
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(1 == type) {//出租
			if(status == 1) {//未上架
				deviceRentOutInfoService.releaseRentOutInfo(id);
			}else {
				deviceRentOutInfoService.cacelRentOutInfo(id);
			}
		}else {
			if(status == 1) {
				deviceRentalInNeedInfoService.releaseNeedInfo(id);
			}else {
				deviceRentalInNeedInfoService.cacelNeedInfo(id);
			}
		}
		resultMap.put("result", "success");
		return JSON.toJSONString(resultMap);

	}
}
