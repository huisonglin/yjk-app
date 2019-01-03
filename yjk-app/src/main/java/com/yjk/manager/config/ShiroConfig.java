package com.yjk.manager.config;

import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import com.yjk.manager.shiro.FormAuthenticationCaptchaFilter;
import com.yjk.manager.shiro.ShiroDbRealm;

@Configuration
public class ShiroConfig {

	
    @Bean
    public ShiroDbRealm shiroDbRealm() {
    	ShiroDbRealm shiroDbRealm = new ShiroDbRealm();
        return shiroDbRealm;
    }
    
	@Bean
	SecurityManager securityManager(ShiroDbRealm shiroDbRealm) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(shiroDbRealm);
		manager.setCacheManager(new EhCacheManager());
		return manager;
	}
	
	@Bean
	ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/loginSuccess");
		shiroFilterFactoryBean.setUnauthorizedUrl("/login");
		Map<String, Filter> filters = new HashMap<>();
		filters.put("authc", new FormAuthenticationCaptchaFilter());
		shiroFilterFactoryBean.setFilters(filters);
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/app/**", "anon");
		filterChainDefinitionMap.put("/servlet/**", "anon");
		/*filterChainDefinitionMap.put("/login", "authc");*/
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/common/file/upload/**", "anon");
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
	
	@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
		proxyCreator.setProxyTargetClass(true);
		return proxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Qualifier("securityManager") SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
}
