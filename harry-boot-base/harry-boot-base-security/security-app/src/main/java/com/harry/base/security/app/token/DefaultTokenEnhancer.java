package com.harry.base.security.app.token;

import com.harry.base.security.core.properties.SecurityProperties;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 * @author harry
 * @version 1.0
 * @title: CustomerTokenEnhancer
 * @description: Token 增强器
 * @date 2019/5/23 17:50
 */
public class DefaultTokenEnhancer implements TokenEnhancer {

  private final SecurityProperties securityProperties;

  public DefaultTokenEnhancer(SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
      OAuth2Authentication authentication) {
    Map<String, Object> info = new HashMap<>();
    info.put("signing", securityProperties.getOauth2().getSigningKey());

    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

    return accessToken;
  }

}
