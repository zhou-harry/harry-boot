package com.harry.base.security.core.endpoint;

import com.harry.base.security.core.constant.SecurityConstants;
import com.harry.base.security.core.validatecode.image.ImageCodeProcessor;
import com.harry.base.security.core.validatecode.sms.SmsCodeProcessor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

@RestController
@AllArgsConstructor
public class ValidateCodeEndpoint {

  //图片验证码生成器
  private final ImageCodeProcessor imageCodeProcessor;
  //短信验证码生成器
  private final SmsCodeProcessor smsCodeProcessor;


  /**
   * 图片验证码
   *
   * @param request
   * @param response
   * @throws Exception
   */
  @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/image")
  public void createCode(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    imageCodeProcessor.process(new ServletWebRequest(request, response));
  }

  /**
   * 短信验证码
   *
   * @param request
   * @param response
   * @throws Exception
   */
  @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/sms")
  public void createSmsCode(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    smsCodeProcessor.process(new ServletWebRequest(request, response));
  }

}
