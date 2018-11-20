package com.yjk.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjk.app.dto.DialingDTO;
import com.yjk.app.service.FeeService;
import com.yjk.app.util.R;

@RestController
@RequestMapping("/fee")
public class FeeController {

	@Autowired
	FeeService feeService;
	
	@RequestMapping("/Dialing")
	public R Dialing(DialingDTO dialingDTO) throws Exception {
		return feeService.Dialing(dialingDTO);
	}
}
