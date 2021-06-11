package com.harry.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class HarryWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(HarryWebApplication.class, args);
    log.info("open http://localhost/doc.html");
    log.info("open http://localhost/swagger-ui.html");
    log.info("open http://localhost/druid/index.html");
  }

}
