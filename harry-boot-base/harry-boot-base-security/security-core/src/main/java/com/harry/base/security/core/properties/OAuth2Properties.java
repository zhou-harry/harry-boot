package com.harry.base.security.core.properties;

import com.harry.base.security.core.constant.SecurityConstants;
import lombok.Data;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;

/**
 * @author harry
 * @version 1.0
 * @title: OAuth2Properties
 * @description: OAuth2属性配置
 * @date 2019/5/23 17:44
 */
@Data
public class OAuth2Properties {

  private String signingKey = SecurityConstants.DEFAULT_OAUTH2_SIGNING_KEY;

  private String storeType = SecurityConstants.DEFAULT_OAUTH2_STORETYPE;

  private OAuth2ClientProperties[] clients = {};

}
