package com.yjk.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjk.app.annotation.LimitedAccessByIP;
import com.yjk.app.annotation.Login;
import com.yjk.app.dto.BindMobileDTO;
import com.yjk.app.dto.ForgotPasswordDTO;
import com.yjk.app.dto.LoginDTO;
import com.yjk.app.dto.ModifyPasswordDTO;
import com.yjk.app.dto.RegisterDTO;
import com.yjk.app.entity.MemberDO;
import com.yjk.app.service.MemberService;
import com.yjk.app.util.R;
import com.yjk.app.validator.Assert;

@RestController
@RequestMapping("/member")
public class ApiMemberController {

	@Autowired
	MemberService memberService;
	
	@Login
	@RequestMapping("register")
	@LimitedAccessByIP(key = "register",EachInterva = 3)
	public R register(RegisterDTO registerDTO) {
		Assert.isBlank(registerDTO.getMobile(), "手机号不能为空");
		Assert.isBlank(registerDTO.getPassword(), "密码不能为空");
		Assert.isBlank(registerDTO.getVerificationCode(), "验证码不能为空");
		R r = memberService.register(registerDTO);
		return r;
	}
	
	
	@RequestMapping("login")
	public R login(LoginDTO loginDTO) {
		Assert.isBlank(loginDTO.getMobile(), "手机号不能为空");
		Assert.isBlank(loginDTO.getPassword(), "密码不能为空");
		R r = memberService.login(loginDTO);
		return r;
	}
	
	@Login
	@RequestMapping("modifyPassword")
	public R modifyPassword(ModifyPasswordDTO modifyPasswordDTO,@RequestAttribute("memberId") Long memberId) {
		modifyPasswordDTO.setMemberId(memberId);
		Assert.isNull(modifyPasswordDTO.getMemberId(), "用户ID不能为空");
		Assert.isBlank(modifyPasswordDTO.getOldPassword(), "原密码不能为空");
		Assert.isBlank(modifyPasswordDTO.getNewPassword(), "新密码不能为空");
		R r = memberService.modifyPassword(modifyPasswordDTO);
		return r;
	}
	
	@RequestMapping("forgotPassword")
	public R forgotPassword(ForgotPasswordDTO forgotPasswordDTO) {
		Assert.isBlank(forgotPasswordDTO.getMobile(), "手机号不能为空");
		Assert.isBlank(forgotPasswordDTO.getNewPassword(), "新密码不能为空");
		Assert.isBlank(forgotPasswordDTO.getVerificationCode(), "验证码不能为空");
		R r = memberService.forgotPassword(forgotPasswordDTO);
		return r;
	}
	
	@Login
	@RequestMapping("editMemberInfo")
	public R editMemberInfo(MemberDO member,@RequestAttribute("memberId") Long id) {
		member.setId(id);
		R r = memberService.editMemberInfo(member);
		return r;
	}
	
	@RequestMapping("loginByXcx")
	public R loginByXcx(String code) throws Exception {
		Assert.isBlank(code, "code不能为空");
		return memberService.loginByXcx(code);
	}
	
	@RequestMapping("bindMobile")
	@LimitedAccessByIP(key="xcxMemberRegister",EachInterva=3)
	public R bindMobile(BindMobileDTO bindMobileDTO) {
		Assert.isBlank(bindMobileDTO.getMobile(), "手机号不能为空");
		Assert.isBlank(bindMobileDTO.getOpenId(), "openId不能为空");
		Assert.isBlank(bindMobileDTO.getVerificationCode(), "验证码不能为空");
		return memberService.bindMobile(bindMobileDTO);
	}
	
	@Login
	@RequestMapping("memberInfo")
	public R memberInfo(@RequestAttribute("memberId") Long memberId) {
		return memberService.memberInfo(memberId);
	}
	
}
