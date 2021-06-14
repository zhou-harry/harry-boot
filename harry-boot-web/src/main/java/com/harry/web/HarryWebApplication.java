package com.harry.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.harry.sso.api.feign"})
public class HarryWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(HarryWebApplication.class, args);
    log.info("open http://localhost/doc.html");
    log.info("open http://localhost/swagger-ui.html");
    log.info("open http://localhost/druid/index.html");
  }

}
