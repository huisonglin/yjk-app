package com.yjk.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjk.app.service.PriceUnitSkuService;
import com.yjk.app.util.R;

@RestController
@RequestMapping("/app/price/unit")
public class ApiPriceUnitSkuServiceController {

	@Autowired
	PriceUnitSkuService priceUnitSkuService;
	
	@RequestMapping("/sku")
	public R sku() {
		return priceUnitSkuService.priceUnitSku();
	}
}
