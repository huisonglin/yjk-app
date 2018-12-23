package com.yjk.manager.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/******
 * 扩展Shiro默认的用户认证的Bean - UsernamePasswordToken
 * @author ATH
 *
 */
public class UsernamePasswordCaptchaToken extends UsernamePasswordToken {

    private static final long serialVersionUID = 1L;

    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public UsernamePasswordCaptchaToken() {
        super();
    }

    public UsernamePasswordCaptchaToken(final String username, final char[] password, final boolean rememberMe,
            final String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }
}
