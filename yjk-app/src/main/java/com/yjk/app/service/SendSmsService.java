package com.yjk.app.service;

import com.yjk.app.util.R;

public interface SendSmsService {

	R sendSMS(String mobile,Integer smsType);
}
