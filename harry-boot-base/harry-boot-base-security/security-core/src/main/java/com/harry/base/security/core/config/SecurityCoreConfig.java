package com.harry.base.security.core.config;

import com.harry.base.db.config.DatabaseConfig;
import com.harry.base.security.core.authorize.server.AuthorizeServer;
import com.harry.base.security.core.authorize.server.BaseAuthorizeServer;
import com.harry.base.security.core.properties.SecurityProperties;
import com.harry.base.security.core.validatecode.sms.DefaultSmsCodeSender;
import com.harry.base.security.core.validatecode.sms.SmsCodeSender;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

import javax.sql.DataSource;

@ComponentScan({"com.harry.base.security.core"})
@EnableConfigurationProperties(SecurityProperties.class)
@AutoConfigureAfter({DatabaseConfig.class})
public class SecurityCoreConfig {

  private final SecurityProperties securityProperties;

  private final DataSource defaultDataSource;

  public SecurityCoreConfig(SecurityProperties securityProperties,DataSource defaultDataSource) {
    this.securityProperties = securityProperties;
    this.defaultDataSource = defaultDataSource;
  }

  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
    jdbcTokenRepository.setDataSource(defaultDataSource);
    jdbcTokenRepository.setCreateTableOnStartup(securityProperties.getInitTokenTableOnStartup());
    return jdbcTokenRepository;
  }


  @Bean
  @ConditionalOnMissingBean(PasswordEncoder.class)
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @ConditionalOnMissingBean(SmsCodeSender.class)
  public SmsCodeSender smsCodeSender() {
    return new DefaultSmsCodeSender();
  }


  /**
   * <br>此处容器会将RedisOperationsSessionRepository给注入进来<br/>
   * (spring redis启动器的场景有效，非启动器环境的话需要显示创建：RedisOperationsSessionRepository)
   *
   * @param sessionRepository
   * @return
   */
  @Bean
  public SpringSessionBackedSessionRegistry redisSessionRegistry(
      FindByIndexNameSessionRepository sessionRepository) {
    return new SpringSessionBackedSessionRegistry(sessionRepository);
  }

  @Bean("authorizeServer")
  public AuthorizeServer authorizeServer() {
    return new BaseAuthorizeServer();
  }

  @Bean
  public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(
      ApplicationContext applicationContext) {
    OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
    expressionHandler.setApplicationContext(applicationContext);
    return expressionHandler;
  }

}
