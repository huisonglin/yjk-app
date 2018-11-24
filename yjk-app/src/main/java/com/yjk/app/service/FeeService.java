package com.yjk.app.service;

import com.yjk.app.dto.DialingDTO;
import com.yjk.app.dto.DialingRefundDTO;
import com.yjk.app.util.R;

public interface FeeService {

	
	public R dialing(DialingDTO dialingDTO) throws Exception ;
	
	public R dialingRefund(DialingRefundDTO dialingRefundDTO) throws Exception;
}
