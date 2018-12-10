package com.yjk.app.controller;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjk.app.common.Constants;
import com.yjk.app.entity.DeviceRentOutInfoDO;
import com.yjk.app.service.InfoDetailService;
import com.yjk.app.util.DatetimeUtil;
import com.yjk.app.util.R;
import com.yjk.app.validator.Assert;
import com.yjk.app.vo.DeviceRentOutInfoDetailVO;
import com.yjk.app.vo.DeviceRentalInNeedInfoDetailVO;

import freemarker.template.utility.DateUtil;

@RestController
@RequestMapping("/info")
public class ApiInfoDetailController {

	@Autowired
	InfoDetailService infoDetailService;
	@Autowired
	ValueOperations<String, String> valueOperations;
	
	@RequestMapping("/detail")
	public R detail(Long id,Integer infoType) throws Exception {
		Assert.isNull(id, "id不能为空");
		Assert.isNull(infoType, "信息类型不能为空");
		Object infoDetail = infoDetailService.infoDetail(id, infoType);
		if(infoDetail instanceof DeviceRentOutInfoDetailVO) {
			DeviceRentOutInfoDetailVO d = (DeviceRentOutInfoDetailVO)infoDetail;
			String lastTime = valueOperations.get(Constants.LAST_LOGIN_TIME+d.getMemberId());
			if(lastTime != null) {
				Date date = new Date(Long.valueOf(lastTime));
				String showTime = DatetimeUtil.showTime(date, null);
				d.setActive(showTime+"活跃");
			}
		}
		if(infoDetail instanceof DeviceRentalInNeedInfoDetailVO) {
			DeviceRentalInNeedInfoDetailVO d = (DeviceRentalInNeedInfoDetailVO)infoDetail;
			String lastTime = valueOperations.get(Constants.LAST_LOGIN_TIME+d.getMemberId());
			if(lastTime != null) {
				Date date = new Date(Long.valueOf(lastTime));
				String showTime = DatetimeUtil.showTime(date, null);
				d.setActive(showTime+"活跃");
			}
		}
		return R.ok().put("info", infoDetail);
	}
}
