package com.yjk.manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yjk.app.dto.DialingRefundDTO;
import com.yjk.app.service.FeeService;
import com.yjk.app.util.R;
import com.yjk.common.dao.MemberMapper;
import com.yjk.common.dao.OrderMapper;
import com.yjk.common.entity.MemberDO;
import com.yjk.common.entity.OrderDO;
import com.yjk.manager.dto.OrderSearchDTO;
import com.yjk.manager.service.OrderManagerService;

@Controller
@RequestMapping("/order")
public class OrderManagerController {

	@Autowired
	OrderManagerService OrderManagerService;
	
	@Autowired
	FeeService feeService;
	
	@RequestMapping(value = "/toList", produces="text/html;charset=UTF-8")
	public String toList() {
		return "order/list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", produces="text/html;charset=UTF-8")
	public String list(String page,OrderSearchDTO dto) {
		return OrderManagerService.orderList(page, dto);
	}
	
	

	@Autowired
	MemberMapper memberMapper;
	
	@ResponseBody
	@RequiresPermissions(value = "per:order:refund")
	@RequestMapping(value = "/refund", produces="text/html;charset=UTF-8")
	public String refund(String orderNo,Long memberId) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		DialingRefundDTO dto = new DialingRefundDTO();
		MemberDO memberDo = memberMapper.selectByPrimaryKey(memberId);
		dto.setOpenId(memberDo.getXcxOpenId());
		dto.setMemberId(memberId);
		dto.setOrderId(orderNo);
		R r = feeService.dialingRefund(dto);
		if(R.SUCCESS.equals(r.get(R.CODE))) {
			resultMap.put("result", "success");
			return JSON.toJSONString(resultMap);	
		}else {
			resultMap.put("result", "fail");
			return JSON.toJSONString(resultMap);
		}

	}
}
