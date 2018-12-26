package com.yjk.manager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yjk.manager.service.AdminUserService;
import com.yjk.manager.shiro.CaptchaException;
import com.yjk.manager.shiro.ShiroDbRealm.ShiroUser;
import com.yjk.manager.shiro.UserFreezeException;
import com.yjk.manager.shiro.UsernamePasswordCaptchaToken;
import com.yjk.manager.utils.MD5;
import com.yjk.manager.vo.UserPermissionMenuVO;



/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 真正登录的POST请求由Filter完成.
 */
@Controller
public class LoginController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AdminUserService userService;


	/******
     * 跳转到登录页面
     * @return
     */
	@RequestMapping(value = {"/{login:login;?.*}"}, method = RequestMethod.GET)   
    public String toLogin() {
		
        return "login/login";
    }
	
	@RequestMapping(value = "/app", method = RequestMethod.GET)   
	public String app() {
		return "index";
	}
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)   
	public String index() {
		return "redirect:/loginSuccess";
	}
    /******
     * 登录成功跳转到登录成功页面(shiro配置文件中配置的)
     * 登录失败跳转到登录页
     * @param username
     * @param password
     * @param captcha
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, String captcha, RedirectAttributes redirectAttributes) {
    	try{
    	   if(StringUtils.isNotBlank(password)){
    		   password = new MD5().getMD5ofStr(password);
    	   }
           SecurityUtils.getSubject().login(new UsernamePasswordCaptchaToken(username, password.toCharArray(), false, "", captcha));
           return "redirect:/loginSuccess";
    	}catch(UnknownAccountException e){
    		//该用户不存在
    		redirectAttributes.addFlashAttribute("msg", "该用户不存在！");
    		redirectAttributes.addFlashAttribute("username", username);
    		return "redirect:/login";
    	}catch(CaptchaException e){
    		//验证码错误，请重试
    		redirectAttributes.addFlashAttribute("msg", "验证码错误，请重试！");
    		redirectAttributes.addFlashAttribute("username", username);
    		return "redirect:/login";
    	}catch(UserFreezeException e){
    		//用户已禁用
    		redirectAttributes.addFlashAttribute("msg", "当前登录用户已被禁用，请联系管理员！");
    		redirectAttributes.addFlashAttribute("username", username);
    		return "redirect:/login";
    	}catch(IncorrectCredentialsException e){
    		//用户或密码错误
    		redirectAttributes.addFlashAttribute("msg", "用户或密码错误，请重试！");
    		redirectAttributes.addFlashAttribute("username", username);
    		return "redirect:/login";
    	}catch(AuthenticationException e){
    		//登录认证错误，请重试
    		redirectAttributes.addFlashAttribute("msg", "登录认证错误，请重试！");
    		redirectAttributes.addFlashAttribute("username", username);
    		return "redirect:/login";
    	}
    }
    
    private List<UserPermissionMenuVO> getAllMenu(List<UserPermissionMenuVO> firstMenuList, ShiroUser user, HttpServletRequest request){
    	//String contextPath = request.getContextPath();
    	//System.out.println("contextPath = " + contextPath);
    	
    	List<UserPermissionMenuVO> menuTreeParents = new ArrayList<UserPermissionMenuVO>();
    	UserPermissionMenuVO firstMenu = null;
    	
    	List<UserPermissionMenuVO> menuTreeChilds = null;
		for (int i = 0; i < firstMenuList.size(); i++) {
			firstMenu = firstMenuList.get(i);
			
			menuTreeChilds = userService.getElseMenuByUserId(user.id, firstMenu.getPerId());
			
			
			firstMenu.setChilds(menuTreeChilds);
			menuTreeParents.add(firstMenu);
		}
		
		return menuTreeParents;
    }

    /******
     * 登录成功处理
     * @return
     */
    @RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
    public String loginSuccess(HttpServletRequest request) {
    	ShiroUser user = getCurrentUserInfo();
    	List<UserPermissionMenuVO> firstMenuList = userService.getFirstMenuByUserId(Long.valueOf(user.id));
    	
    	List<UserPermissionMenuVO> userMenuList = getAllMenu(firstMenuList, user, request);
    	request.setAttribute("name", user.getNickName());
    	request.setAttribute("menus", userMenuList);
    	return "desktop";
    	
    }
    
    /******
     * 欢迎页面
     * @return
     */
    @RequestMapping("/welcome")
    public String welcome(HttpServletRequest request) {
/*    	//待审核车辆
    	Integer dshCarCount = carBaseService.selectCarCountBySelective(null, CarStatus.DAISHENHE.getValue());
    	//待分配卖车信息
    	Integer dfpSellerInfoCount = sellerInfoService.selectCountBySelective(null, SellerInfoStatus.INFO_ED.getValue());
    	//待分配买车信息
    	Integer dfpBuyerInfoCount = buyerInfoService.selectCountBySelective(null, BuyerInfoStatus.INFO_ED.getValue());
    	//在售车辆总数
    	Integer zsCarCount = carBaseService.selectCarCountBySelective(null, CarStatus.YISHANGJIA.getValue());
    	//已成交车辆总数
    	Integer ycjCarCount = carBaseService.selectCarCountBySelective(null, CarStatus.YIMAICHU.getValue());
    	//门店数
    	Integer storeCount = storeService.selectCountBySelective(StoreStatus.ON.getValue());
    	
    	request.setAttribute("dshCarCount", dshCarCount);
    	request.setAttribute("dfpSellerInfoCount", dfpSellerInfoCount);
    	request.setAttribute("dfpBuyerInfoCount", dfpBuyerInfoCount);
    	request.setAttribute("zsCarCount", zsCarCount);
    	request.setAttribute("ycjCarCount", ycjCarCount);
    	request.setAttribute("storeCount", storeCount);*/
    	return "welcome";
    }
    
    /******
     * 安全登出
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes) {
    	SecurityUtils.getSubject().logout();
    	
    	redirectAttributes.addFlashAttribute("msg", "安全退出成功！");
    	return "redirect:/login";
    }
    
}
