package com.harry.sso.web;

import com.harry.sso.web.properties.SSOWebProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(SSOWebProperties.class)
public class SSOWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSOWebApplication.class, args);
        log.info("open http://localhost/sso-web/doc.html");
        log.info("open http://localhost/sso-web/swagger-ui.html");
        log.info("open http://localhost/sso-web/druid/index.html");
    }
}
