package com.yjk.manager.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yjk.manager.dto.SearchUserDTO;
import com.yjk.manager.service.MemberManagerService;

@Controller
@RequestMapping("/member")
public class MemberManagerController {
	
	@Autowired
	MemberManagerService memberManagerService;

	@RequestMapping(value = "/toList", produces="text/html;charset=UTF-8")
	public String toList() {
		return "member/list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", produces="text/html;charset=UTF-8")
	public String list(String page,SearchUserDTO dto) {
		return memberManagerService.userList(page, dto);
	}
}
