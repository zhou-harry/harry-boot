package com.harry.sso.app;

import com.harry.sso.app.properties.SSOAppProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(SSOAppProperties.class)
public class HarrySSOAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(HarrySSOAppApplication.class, args);
    log.info("open http://localhost/sso-app/doc.html");
    log.info("open http://localhost/sso-app/swagger-ui.html");
    log.info("open http://localhost/sso-app/druid/index.html");
  }

}
