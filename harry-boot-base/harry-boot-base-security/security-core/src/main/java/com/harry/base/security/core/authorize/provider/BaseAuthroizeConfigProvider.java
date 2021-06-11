package com.harry.base.security.core.authorize.provider;

import com.harry.base.security.core.constant.SecurityConstants;
import com.harry.base.security.core.properties.BrowserProperties;
import com.harry.base.security.core.properties.SecurityProperties;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author harry
 * @version 1.0
 * @title: BaseAuthroizeConfigProvider
 * @description: 基础授权配置
 * @date 2019/5/24 15:13
 */
@Component
@Order(Integer.MAX_VALUE - 100)
public class BaseAuthroizeConfigProvider implements AuthorizeConfigProvider {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        //系统中需要放行的请求
        BrowserProperties browser = securityProperties.getBrowser();
        List<String> premits = Arrays.asList(
            browser.getSignInUrl(),
            browser.getSignUpUrl(),
            browser.getSignOutUrl(),
            browser.getSigninProcessUrlForm(),
            browser.getSigninProcessUrlMobile(),
            browser.getSigninProcessUrlOpenId(),
            browser.getSigninProcessUrlMobile(),
            browser.getSession().getSessionInvalidUrl(),
            browser.getLoginPage(),
            browser.getLogoutPage()
        ).stream().filter(url -> !StringUtils.isEmpty(url)).collect(Collectors.toList());
        config
                .antMatchers(SecurityConstants.MATCHERS).permitAll()
                .antMatchers(browser.getPermitUrl()).permitAll()
                .antMatchers(premits.toArray(new String[premits.size()])).permitAll()
        ;
    }
}