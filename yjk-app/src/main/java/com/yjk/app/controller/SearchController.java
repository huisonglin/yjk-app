package com.yjk.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yjk.app.service.SearchService;
import com.yjk.app.util.R;
import com.yjk.app.validator.Assert;

@RestController
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	public R search(Integer pageNo) throws Exception {
		Assert.isNull(pageNo, "页码不能为空");
		R search = searchService.search(pageNo);
		return search;
	}
}
