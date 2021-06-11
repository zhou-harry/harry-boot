package com.harry.base.security.core.properties;

import lombok.Data;

/**
 * @author harry
 * @version 1.0
 * @title: ValidateCodeProperties
 * @description: TODO
 * @date 2019/5/12 0:26
 */
@Data
public class ValidateCodeProperties {


  private ImageCodeProperties image = new ImageCodeProperties();

  private SmsCodeProperties sms = new SmsCodeProperties();

  /**
   * 图片验证码默认配置
   */
  @Data
  public class ImageCodeProperties {

    private int width = 100;    //图片宽
    private int height = 40;   //图片高
    private int length = 4;    //验证码长度
    private int expireIn = 60; //失效时间
    private String url;        //多个请求需要验证；逗号隔开

  }

  /**
   * 短信验证码默认配置
   */
  @Data
  public class SmsCodeProperties {

    //验证码字符个数
    private int length = 4;
    //过期时间
    private int expireIn = 60;
    //拦截的url
    private String url;
  }

}
