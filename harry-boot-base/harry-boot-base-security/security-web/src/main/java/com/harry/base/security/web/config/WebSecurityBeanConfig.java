package com.harry.base.security.web.config;

import com.harry.base.security.core.properties.SecurityProperties;
import com.harry.base.security.core.validatecode.ValidateCodeRepository;
import com.harry.base.security.web.handler.WebAuthenticationFailureHandler;
import com.harry.base.security.web.handler.WebAuthenticationSuccessHandler;
import com.harry.base.security.web.handler.WebLogoutSuccessHandler;
import com.harry.base.security.web.session.WebInvalidSessionStrategy;
import com.harry.base.security.web.session.WebSessionExpiredStrategy;
import com.harry.base.security.web.session.repository.SessionValidateCodeRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

@Configuration
@ComponentScan({"com.harry.base.security.web"})
public class WebSecurityBeanConfig {

  private final SecurityProperties securityProperties;

  public WebSecurityBeanConfig(SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }

  @Bean
  @ConditionalOnMissingBean(LogoutSuccessHandler.class)
  public LogoutSuccessHandler logoutSuccessHandler() {
    return new WebLogoutSuccessHandler(securityProperties);
  }

  @Bean
  @ConditionalOnMissingBean(name = "authenticationSuccessHandler")
  public AuthenticationSuccessHandler authenticationSuccessHandler() {
    return new WebAuthenticationSuccessHandler();
  }

  @Bean
  @ConditionalOnMissingBean(name = "authenticationFailureHandler")
  public AuthenticationFailureHandler authenticationFailureHandler() {
    return new WebAuthenticationFailureHandler();
  }


  @Bean
  @ConditionalOnMissingBean(InvalidSessionStrategy.class)
  public InvalidSessionStrategy invalidSessionStrategy() {
    return new WebInvalidSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
  }

  @Bean
  @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
  public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
    return new WebSessionExpiredStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
  }

  @Bean
  @ConditionalOnMissingBean(ValidateCodeRepository.class)
  public ValidateCodeRepository validateCodeRepository() {
    return new SessionValidateCodeRepository();
  }
}
