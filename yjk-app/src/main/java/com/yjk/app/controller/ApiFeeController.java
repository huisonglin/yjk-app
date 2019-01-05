package com.yjk.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjk.app.annotation.LimitedAccessByToken;
import com.yjk.app.annotation.Login;
import com.yjk.app.annotation.LoginUser;
import com.yjk.app.dto.DialingDTO;
import com.yjk.app.dto.DialingRefundDTO;
import com.yjk.app.service.FeeService;
import com.yjk.app.util.R;
import com.yjk.app.validator.Assert;
import com.yjk.common.entity.MemberDO;

@RestController
@RequestMapping("/app/fee")
public class ApiFeeController {

	@Autowired
	FeeService feeService;
	
	@Login
	@RequestMapping("/dialing")
	@LimitedAccessByToken(key="Dialing",EachInterva=3)
	public R Dialing(DialingDTO dialingDTO,@LoginUser MemberDO memberDO) throws Exception {
		Assert.isNull(dialingDTO.getInfoId(), "信息ID不能为空");
		dialingDTO.setMemberId(memberDO.getId());
		dialingDTO.setOpenId(memberDO.getXcxOpenId());
		return feeService.dialing(dialingDTO);
	}
	
	@Login
	@RequestMapping("/dialingDesc")
	@LimitedAccessByToken(key="dialingDesc",EachInterva=3)
	public R dialingDesc(DialingDTO dialingDTO,@RequestAttribute("memberId")Long memberId) {
		dialingDTO.setMemberId(memberId);
		return feeService.dialingDesc(dialingDTO);
	}
	
	@Login
	@RequestMapping("/dialing/refund")
	@LimitedAccessByToken(key="dialingRefund",EachInterva=3)
	public R dialingRefund(DialingRefundDTO dialingRefundDTO, @LoginUser MemberDO memberDO) throws Exception {
		Assert.isBlank(dialingRefundDTO.getOrderId(), "订单ID不能为空");
		dialingRefundDTO.setMemberId(memberDO.getId());
		dialingRefundDTO.setOpenId(memberDO.getXcxOpenId());
		return feeService.dialingRefund(dialingRefundDTO);
	}
	

}
