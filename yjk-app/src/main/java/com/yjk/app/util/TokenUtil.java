package com.yjk.app.util;

import org.springframework.util.StringUtils;

/**
 * @author wuwei
 * @date 2018/5/30 10:52
 */
public class TokenUtil {
    /**
     * 根据用户名、密码和过期时间生成token
     *
     * @param username
     * @param password
     * @return
     */
    public static String getToken(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) return null;
        //TODO 生成token
        return null;
    }

    /**
     * token验证
     *
     * @param token
     * @return
     */
    public static boolean checkToken(String token) {
        if (StringUtils.isEmpty(token)) return false;
        //TODO 解析token
        return true;
    }
}
