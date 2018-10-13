/*package com.yjk.app.controller;

import com.yjk.app.annotation.DistributedLock;
import com.yjk.app.annotation.LimitedAccessByIP;
import com.yjk.app.annotation.LimitedAccessByToken;
import com.yjk.app.annotation.Login;
import com.yjk.app.annotation.LoginUser;
import com.yjk.app.entity.Result;
import com.yjk.app.entity.User;
import com.yjk.app.interceptor.AuthorizationInterceptor;
import com.yjk.app.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

*//**
 * @author wuwei
 * @date 2018/5/24 9:40
 *//*
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/addUser")
    public Result addUser(User user) {
        return userService.addUser(user);
    }

    //@Login
    @DistributedLock(lockTime=1000*1)
    @RequestMapping("/findAllUser")
    //@LimitedAccessByToken(key="certication",EachInterva=10,timesOfDay=3)
    public Object findAllUser(String userId ,@LoginUser User user) throws InterruptedException {
    	//System.out.println(user);
    	System.out.println(userId);
        return userService.findAllUser();
    }
    

    @RequestMapping("/findUserById")
    public Result findUserById(@RequestParam("userId") String userId) {
        return userService.findUserById(userId);
    }

    @RequestMapping("/updateUser")
    public Result updateUser(User user) {
        return userService.updateUser(user);
    }

    @RequestMapping("/delUser")
    public Result delUser(@RequestParam("userId") String userId) {
        return userService.delUser(userId);
    }

    @RequestMapping("/delUsers")
    public Result delUsers(@RequestParam("userIds") String userIds) {
        return userService.delUsers(userIds);
    }
}
*/