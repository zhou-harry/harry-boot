package com.harry.sso.api.config;

import com.harry.sso.api.properties.SSOApiProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SSOApiProperties.class)
public class SSOApiConfig {

}
