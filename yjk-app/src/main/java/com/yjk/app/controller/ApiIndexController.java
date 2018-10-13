package com.yjk.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApiIndexController {

	@RequestMapping("toUpload")
	public String toUpload() {
		return "uploadMany";
	}
}
