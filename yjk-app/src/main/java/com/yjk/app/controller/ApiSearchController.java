package com.yjk.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yjk.app.annotation.Position;
import com.yjk.app.dto.PositionDTO;
import com.yjk.app.dto.SearchDTO;
import com.yjk.app.service.SearchService;
import com.yjk.app.util.R;
import com.yjk.app.validator.Assert;

@RestController
public class ApiSearchController {

	@Autowired
	private SearchService searchService;
	
/*	<delete><query>*:*</query></delete>
	<commit/>*/
	
	@RequestMapping("/search")
	public R search(SearchDTO searchDTO  ,@Position PositionDTO positionDTO) throws Exception {
		Assert.isNull(searchDTO.getPageNo(),"请输入页码");
		if(searchDTO.getPageNo() == null || searchDTO.getPageNo() < 1) {
			searchDTO.setPageNo(1);
		}
		Assert.isNull(searchDTO.getDistance(), "检索范围不能为空");
		searchDTO.setPositionDTO(positionDTO);
		R search = searchService.search(searchDTO);
		return search;
	}
	
}
