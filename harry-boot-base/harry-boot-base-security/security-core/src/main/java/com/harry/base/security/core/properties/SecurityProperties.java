package com.harry.base.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "harry.base.security")
public class SecurityProperties {

  private BrowserProperties browser = new BrowserProperties();

  private ValidateCodeProperties validateCode = new ValidateCodeProperties();

  private SocialProperties social = new SocialProperties();

  private OAuth2Properties oauth2 = new OAuth2Properties();

  //启动时初始化token表
  private Boolean initTokenTableOnStartup = false;

}
