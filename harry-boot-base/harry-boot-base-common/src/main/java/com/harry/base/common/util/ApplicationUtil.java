package com.harry.base.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class ApplicationUtil implements ApplicationContextAware {

  private static ApplicationContext context;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context = applicationContext;
  }

  public static ApplicationContext getContext() {
    return context;
  }

  /**
   * 获取 MessageSourceAccessor
   * @return
   */
  public static MessageSourceAccessor getMSA() {
    MessageSourceAccessor messageSourceAccessor = context
        .getBean(MessageSourceAccessor.class);
    return messageSourceAccessor;
  }

}
