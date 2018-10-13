package com.yjk.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yjk.app.util.QiNiuUtils;
import com.yjk.app.util.R;

@RestController
@RequestMapping("/upload")
public class ApiUploadController {

	public  static String detail = "-cc";
	

	@RequestMapping("/data")
	public R ups(@RequestParam("file") MultipartFile[] files,
            HttpServletRequest request) throws IOException {
		Long startTime = System.currentTimeMillis();		
		List<String> images = new ArrayList<>();
		for (MultipartFile multipartFile : files) {
			String upload = QiNiuUtils.upload(multipartFile.getBytes());
			upload = "http://img.huisonglin.top/"+upload+detail;
			System.out.println(upload);	
			images.add(upload);
		}
		Long endTime = System.currentTimeMillis();
		System.out.println("本次请求处理时间为："+new Long(endTime-startTime)+"ms");
		return R.ok().put("data", images);
	}
}
