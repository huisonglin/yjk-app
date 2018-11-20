package com.yjk.app.service.impl;


import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yjk.app.dto.DialingDTO;
import com.yjk.app.dto.XcxUnifiedorderDTO;
import com.yjk.app.service.FeeService;
import com.yjk.app.util.PayUtil;
import com.yjk.app.util.R;
import com.yjk.app.util.UuidUtils;


@Service
public class FeeServiceImpl implements FeeService{

	@Autowired
	PayUtil payUtil;
	
	public R Dialing(DialingDTO dialingDTO) throws Exception {
		XcxUnifiedorderDTO xcxUnifiedorderDTO = new XcxUnifiedorderDTO();
		xcxUnifiedorderDTO.setOpenid("osLl_4hs4cKoCt1D4MAd_JTL0pgc");
		xcxUnifiedorderDTO.setBody("商品描述");
		xcxUnifiedorderDTO.setDetail("商品詳情");
		xcxUnifiedorderDTO.setNonce_str(UuidUtils.get32UUID());
		xcxUnifiedorderDTO.setOut_trade_no(UuidUtils.generateOrderNo("NO"));
		xcxUnifiedorderDTO.setSpbill_create_ip("36.5.113.204");
		xcxUnifiedorderDTO.setTotal_fee(new BigDecimal("0.01"));
		return payUtil.xcxAccessPayParam(xcxUnifiedorderDTO);
	}


}
