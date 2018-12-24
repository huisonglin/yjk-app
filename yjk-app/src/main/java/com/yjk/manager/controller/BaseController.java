package com.yjk.manager.controller;

import org.apache.shiro.SecurityUtils;

import com.yjk.manager.shiro.ShiroDbRealm.ShiroUser;

public class BaseController {

    /**
     * 取出Shiro中的当前用户
     */
    protected ShiroUser getCurrentUserInfo() {
    	ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user;
    }
}
