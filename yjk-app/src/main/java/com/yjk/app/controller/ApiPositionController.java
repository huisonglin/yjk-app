package com.yjk.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yjk.app.annotation.Position;
import com.yjk.app.dto.PositionDTO;

@RestController
public class ApiPositionController {


	@RequestMapping("/app/postion")
	public PositionDTO postion(@Position PositionDTO positionDTO) {
		return positionDTO;
	}
}
