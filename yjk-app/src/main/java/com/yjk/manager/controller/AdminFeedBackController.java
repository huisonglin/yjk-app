package com.yjk.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjk.manager.dto.OrderSearchDTO;
import com.yjk.manager.service.AdminfeedBackService;

@Controller
@RequestMapping("/feedBack")
public class AdminFeedBackController {

	@Autowired
	AdminfeedBackService adminfeedBackService;
	
	@RequestMapping(value = "/toList", produces="text/html;charset=UTF-8")
	public String toList() {
		return "feedBack/list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", produces="text/html;charset=UTF-8")
	public String list(String page,OrderSearchDTO dto) {
		return adminfeedBackService.feedBackList(page);
	}
}
