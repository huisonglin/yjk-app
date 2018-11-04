package com.yjk.app.service;

import com.yjk.app.dto.BindMobileDTO;
import com.yjk.app.dto.ForgotPasswordDTO;
import com.yjk.app.dto.LoginDTO;
import com.yjk.app.dto.ModifyPasswordDTO;
import com.yjk.app.dto.RegisterDTO;
import com.yjk.app.entity.MemberDO;
import com.yjk.app.util.R;

public interface MemberService {

	 MemberDO selectByPrimaryKey(Long id);
	 
	 R login(LoginDTO loginDTO);
	 
	 R register(RegisterDTO registerDTO);
	 
	 R modifyPassword(ModifyPasswordDTO modifyPasswordDTO);
	 
	 R forgotPassword(ForgotPasswordDTO forgotPasswordDTO);
	 
	 R editMemberInfo(MemberDO member);
	 
	 R loginByXcx(String code);
	 
	 R loginByXcxNeedMobile(String code);
	 
	 R bindMobile(BindMobileDTO bindMobileDTO);
	 
	 R memberInfo(Long memberId);
}
