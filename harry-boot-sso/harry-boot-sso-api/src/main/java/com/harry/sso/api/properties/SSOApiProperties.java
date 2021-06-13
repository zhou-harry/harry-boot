package com.harry.sso.api.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "harry.sso.api")
public class SSOApiProperties {

  private String providerServiceName;

  private String providerServiceUrl;
}
