package com.harry.base.security.web.config;

import com.harry.base.security.core.authentication.mobile.SmsCodeAuthenticationConfig;
import com.harry.base.security.core.authorize.manager.AuthorizeConfigManager;
import com.harry.base.security.core.properties.SecurityProperties;
import com.harry.base.security.core.validatecode.ValidateCodeConfig;
import com.harry.base.security.core.web.AbstractSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.social.security.SpringSocialConfigurer;

@EnableWebSecurity
public class WebSecurityConfig extends AbstractSecurityConfig {

  @Value("server.servlet.session.cookie.name")
  private String cookieName;
  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private PersistentTokenRepository persistentTokenRepository;
  @Autowired
  private SecurityProperties securityProperties;
  @Autowired
  private SmsCodeAuthenticationConfig smsCodeAuthenticationConfig;
  @Autowired
  private ValidateCodeConfig validateCodeSecurityConfig;
  @Autowired(required = false)
  private SpringSocialConfigurer socialSecurityConfig;
  @Autowired
  private SessionInformationExpiredStrategy expiredSessionStrategy;
  @Autowired
  private InvalidSessionStrategy invalidSessionStrategy;
  @Autowired
  private SpringSessionBackedSessionRegistry redisSessionRegistry;
  @Autowired
  private LogoutSuccessHandler logoutSuccessHandler;
  @Autowired
  private AuthorizeConfigManager authorizeConfigManager;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    logger.debug("??????????????????,cookieName: " + cookieName);

    //????????????????????????
    this.applyPasswordAuthConfig(http);
    //??????????????????
    if (null!=socialSecurityConfig){
      http.apply(socialSecurityConfig);
    }
    http
        //???????????????????????????
        .apply(validateCodeSecurityConfig)
        .and()//??????????????????
        .apply(smsCodeAuthenticationConfig)
        .and()//Remember me ????????????
        .rememberMe()
        .tokenRepository(persistentTokenRepository)
        .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
        .userDetailsService(userDetailsService)
        .and()//Session ????????????
        .sessionManagement()
        .invalidSessionStrategy(invalidSessionStrategy)
        .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
        .maxSessionsPreventsLogin(
            securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
        .expiredUrl(securityProperties.getBrowser().getSession().getSessionInvalidUrl())
        .expiredSessionStrategy(expiredSessionStrategy)
        .sessionRegistry(redisSessionRegistry)
        .and()
        .and()//??????
        .logout()
        .logoutUrl(securityProperties.getBrowser().getLogoutPage())
        .logoutSuccessHandler(logoutSuccessHandler)
        .deleteCookies(cookieName)
        .and().csrf().disable()
    ;
    authorizeConfigManager.config(http.authorizeRequests());
  }

}
