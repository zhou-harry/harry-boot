package com.harry.base.security.app.config;

import com.harry.base.security.core.authentication.mobile.SmsCodeAuthenticationConfig;
import com.harry.base.security.core.authentication.openid.OpenIdAuthenticationConfig;
import com.harry.base.security.core.authorize.manager.AuthorizeConfigManager;
import com.harry.base.security.core.properties.SecurityProperties;
import com.harry.base.security.core.validatecode.ValidateCodeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author harry
 * @version 1.0
 * @title: AppResourceServerConfig
 * @description: Oauth2资源服务配置
 * @date 2019/5/20 11:31
 */
@Configuration
@EnableResourceServer
public class AppResourceServerConfig extends ResourceServerConfigurerAdapter {

  protected Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private SecurityProperties securityProperties;
  @Autowired
  private AuthenticationFailureHandler authenticationFailureHandler;
  @Autowired
  private AuthenticationSuccessHandler authenticationSuccessHandler;
  @Autowired
  private SmsCodeAuthenticationConfig smsCodeAuthenticationConfig;
  @Autowired
  private ValidateCodeConfig validateCodeSecurityConfig;
  @Autowired(required = false)
  private SpringSocialConfigurer socialSecurityConfig;
  @Autowired(required = false)
  private OpenIdAuthenticationConfig openIdAuthenticationConfig;
  @Autowired
  private AuthorizeConfigManager authorizeConfigManager;
  @Autowired
  private OAuth2WebSecurityExpressionHandler expressionHandler;


  @Override
  public void configure(HttpSecurity http) throws Exception {
    logger.debug("App认证资源配置");
    if (null != socialSecurityConfig) {
      http.apply(socialSecurityConfig)//社交验证配置
          .and()
          .apply(openIdAuthenticationConfig);//OPenId验证配置;
    }
    http
        .apply(validateCodeSecurityConfig)//自定义验证码过滤器
        .and()
        .apply(smsCodeAuthenticationConfig)//手机号认证配置
        .and()
        .formLogin()
        .loginPage(securityProperties.getBrowser().getLoginPage())
        .loginProcessingUrl(securityProperties.getBrowser().getSigninProcessUrlForm())
        .successHandler(authenticationSuccessHandler)
        .failureHandler(authenticationFailureHandler)
        .and().csrf().disable()
    ;
    authorizeConfigManager.config(http.authorizeRequests());
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.expressionHandler(expressionHandler);
  }

}
