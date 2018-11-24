package com.yjk.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.fabric.xmlrpc.base.Member;
import com.yjk.app.annotation.Login;
import com.yjk.app.annotation.LoginUser;
import com.yjk.app.dto.DialingDTO;
import com.yjk.app.dto.DialingRefundDTO;
import com.yjk.app.entity.MemberDO;
import com.yjk.app.service.FeeService;
import com.yjk.app.util.R;
import com.yjk.app.validator.Assert;

@RestController
@RequestMapping("/fee")
public class FeeController {

	@Autowired
	FeeService feeService;
	
	@Login
	@RequestMapping("/dialing")
	public R Dialing(DialingDTO dialingDTO,@LoginUser MemberDO memberDO) throws Exception {
		Assert.isNull(dialingDTO.getInfoId(), "信息ID不能为空");
		dialingDTO.setMemberId(memberDO.getId());
		dialingDTO.setOpenId(memberDO.getXcxOpenId());
		return feeService.dialing(dialingDTO);
	}
	
	@Login
	@RequestMapping("/dialing/refund")
	public R dialingRefund(DialingRefundDTO dialingRefundDTO, @LoginUser MemberDO memberDO) throws Exception {
		Assert.isBlank(dialingRefundDTO.getOrderId(), "订单ID不能为空");
		dialingRefundDTO.setMemberId(memberDO.getId());
		dialingRefundDTO.setOpenId(memberDO.getXcxOpenId());
		return feeService.dialingRefund(dialingRefundDTO);
	}
}
