package com.yjk.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.yjk.app.config.QiNiuConfig;
import com.yjk.app.util.QiNiuUtils;
import com.yjk.app.util.R;
import com.yjk.app.vo.ImageVO;

@Controller
@RequestMapping("/app/upload")
public class ApiUploadController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	

	@ResponseBody
	@RequestMapping("/data")
	public R ups(@RequestParam("file") MultipartFile[] files,
            HttpServletRequest request) throws IOException {
		Long startTime = System.currentTimeMillis();		
		List<ImageVO> images = new ArrayList<>();
		for (MultipartFile multipartFile : files) {
			//String originalFilename = multipartFile.getOriginalFilename();
			String url = QiNiuUtils.upload(multipartFile.getBytes(),null);
			url = QiNiuConfig.DOMAIN+url;
			ImageVO iv = new ImageVO();
			iv.setUrl(url);
			iv.setSuffix(QiNiuConfig.XCX_THUMBNAIL);
			images.add(iv);
		}
		Long endTime = System.currentTimeMillis();
		logger.info("本次请求处理时间为："+new Long(endTime-startTime)+"ms");
		R r = R.ok().put("info", images);
		return r;
	}
}


