package com.harry.base.common.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

  @Value("${spring.application.name}")
  private String appName;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    log.info("=======================【{}】启动完成==================================",appName);
  }
}
