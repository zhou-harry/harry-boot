package com.harry.base.security.core.validatecode.sms;

import static com.harry.base.security.core.constant.SecurityConstants.SERVLET_REQUEST_PARAM_MOBILE;

import com.harry.base.security.core.constant.SecurityConstants;
import com.harry.base.security.core.validatecode.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author harry
 * @version 1.0
 * @title: SmsCodeProcessor
 * @description: 短信验证码处理器
 * @date 2019/5/11 22:59
 */
@Component(SecurityConstants.DEFAULT_SMS_CODE_PROCESSOR)
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<SmsCode, SmsCodeGenerator> {

  @Autowired
  private SmsCodeGenerator smsCodeGenerator;

  @Autowired
  private SmsCodeSender smsCodeSender;

  @Override
  protected SmsCodeGenerator getValidateCodeGenerator() {
    return smsCodeGenerator;
  }

  @Override
  protected void send(ServletWebRequest request, SmsCode validateCode) throws Exception {
    //获取手机号
    String mobile = ServletRequestUtils
        .getRequiredStringParameter(request.getRequest(), SERVLET_REQUEST_PARAM_MOBILE);
    //发送短信验证码
    smsCodeSender.send(mobile, validateCode.getCode());
  }
}
