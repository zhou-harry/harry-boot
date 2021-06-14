package com.harry.sso.app;

import com.harry.sso.app.properties.SSOAppProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(SSOAppProperties.class)
public class SSOAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(SSOAppApplication.class, args);
    log.info("open http://localhost/sso-app/doc.html");
    log.info("open http://localhost/sso-app/swagger-ui.html");
    log.info("open http://localhost/sso-app/druid/index.html");
  }

}
