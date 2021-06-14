package com.harry.sso.web.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "harry.base.sso.web")
public class SSOWebProperties {
}
