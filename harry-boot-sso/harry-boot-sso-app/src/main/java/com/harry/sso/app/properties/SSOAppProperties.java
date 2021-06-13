package com.harry.sso.app.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "harry.base.sso.app")
public class SSOAppProperties {


}
