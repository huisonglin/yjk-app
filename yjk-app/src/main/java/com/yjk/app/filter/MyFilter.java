package com.yjk.app.filter;

import com.alibaba.fastjson.JSONObject;
import com.yjk.app.util.TokenUtil;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * 过滤器
 *
 * @author wuwei
 * @date 2017-8-19 11:09:03
 */
/*@Component
@WebFilter(urlPatterns = "/**")*/
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器：初始化...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("过滤器：过滤前...");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.setContentType("application/json;charset=UTF-8");
        String accessToken = req.getParameter("accessToken");
        if (TokenUtil.checkToken(accessToken)) {
            chain.doFilter(request, response);
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", "1");
            jsonObject.put("data", "accessToken错误或已失效！");
            res.getWriter().print(jsonObject);
        }
        System.out.println("过滤器：过滤后...");
    }

    @Override
    public void destroy() {
        System.out.println("过滤器：销毁...");
    }
}
