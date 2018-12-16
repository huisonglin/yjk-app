package com.yjk.app.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 监听器
 *
 * @author wuwei
 * @date 2017-8-26 9:42:21
 */
@WebListener
public class MyListener implements ServletContextListener {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("监听器：ServletContext初始化...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("监听器：ServletContext销毁...");
    }
}
