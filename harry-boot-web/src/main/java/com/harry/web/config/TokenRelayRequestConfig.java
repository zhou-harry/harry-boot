package com.harry.web.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class TokenRelayRequestConfig implements RequestInterceptor {

  private static String AUTH_TOKEN="authorization";
  @Override
  public void apply(RequestTemplate template) {
    // 获取该次请求得token 将token传递
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    String token = request.getHeader(AUTH_TOKEN);
    if (StringUtils.isNotBlank(token)) {
      template.header(AUTH_TOKEN, token);
    }
  }

}
