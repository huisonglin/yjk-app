package com.yjk.manager.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import com.yjk.manager.common.Constants;
import com.yjk.manager.utils.CaptchaUtil;


@Controller
@RequestMapping("/servlet")
public class CaptchaController {

	@RequestMapping("/captchaCode")
	public void generateCaptchaCode(HttpServletResponse resp,HttpServletRequest req) {
        // 设置相应类型,告诉浏览器输出的内容为图片 
        resp.setContentType("image/jpeg");
        // 不缓存此内容 
        resp.setHeader("Pragma", "No-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expire", 0);
        try {
            CaptchaUtil tool = new CaptchaUtil();
            StringBuffer code = new StringBuffer();
            BufferedImage image = tool.genRandomCodeImage(code);
            WebUtils.setSessionAttribute(req, Constants.KEY_CAPTCHA, code.toString());
            // 将内存中的图片通过流动形式输出到客户端 
            ImageIO.write(image, "JPEG", resp.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
