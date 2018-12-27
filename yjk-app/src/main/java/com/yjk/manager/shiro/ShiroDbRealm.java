package com.yjk.manager.shiro;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.yjk.manager.common.Constants;
import com.yjk.manager.common.UserStatus;
import com.yjk.manager.service.admin.AdminUserService;
import com.yjk.manager.vo.admin.AdminUserVO;
import com.yjk.manager.vo.admin.UserPermissionVO;

/**
 * 自实现用户与权限查询.
 * 密码用明文存储，因此使用默认 的SimpleCredentialsMatcher.
 */
public class ShiroDbRealm extends AuthorizingRealm {

    private AdminUserService adminUserService;

    @Autowired
    public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

    /**
     * 认证回调函数, 登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;

        String username = token.getUsername();
        if (StringUtils.isBlank(username)) {
            throw new AccountException("Null usernames are not allowed by this realm!");
        }
        // 增加判断验证码逻辑 
        String captcha = token.getCaptcha();
        String exitCode = (String) SecurityUtils.getSubject().getSession().getAttribute(Constants.KEY_CAPTCHA);
        if (StringUtils.isEmpty(captcha) || !captcha.equalsIgnoreCase(exitCode)) {
            throw new CaptchaException("Captcha error!");
        }

        AdminUserVO user = adminUserService.getUserByLoginName(token.getUsername());

        if (null == user) {
            throw new UnknownAccountException("No account found for user [" + username + "]!");
        } else {
        	
        	if(user.getStatus().equals(UserStatus.OFF.getValue())){
        		throw new UserFreezeException("Login user freeze!");
        	}
        	
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(new ShiroUser(user.getUserId(),
                    user.getLoginName(), user.getNickName()), //用户
                    user.getPassword(), //密码
                    getName() //realm name
            );
            return authenticationInfo;
        }
    }
    

	/**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        
        List<UserPermissionVO> userPerList = adminUserService.selectPermissionByUserId(shiroUser.id);
        for (UserPermissionVO userPer : userPerList) {
        	info.addStringPermission(userPer.getPermission());
		}
        return info;
    }

    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }

    /**
     * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
     */
    public static class ShiroUser implements Serializable {
        private static final long serialVersionUID = -1373760761780840081L;
        public Long id;
        public String loginName;
        public String nickName;

        public ShiroUser(Long id, String loginName, String nickName) {
            this.id = id;
            this.loginName = loginName;
            this.nickName = nickName;
        }

        public Long getId() {
            return id;
        }
        
        public String getLoginName() {
            return loginName;
        }

        public String getNickName() {
            return nickName;
        }
    }
}
