package com.harry.base.security.core.properties;

import com.harry.base.security.core.constant.AuthenticationResponseTypeEnum;
import com.harry.base.security.core.constant.SecurityConstants;
import lombok.Data;

/**
 * @author harry
 * @version 1.0
 * @title: BrowserProperties
 * @description: 表单属性配置
 * @date 2019/5/11 23:45
 */
@Data
public class BrowserProperties {

  //表单身份认证地址
  private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE;
  //账号退出地址
  private String logoutPage = SecurityConstants.DEFAULT_LOGOUT_PAGE;
  //用户名密码登录请求处理url
  private String signinProcessUrlForm = SecurityConstants.DEFAULT_SIGNIN_PROCESS_URL_FORM;
  //手机验证码登录请求处理url
  private String signinProcessUrlMobile = SecurityConstants.DEFAULT_SIGNIN_PROCESS_URL_MOBILE;
  //OpenID认证登录请求处理url
  private String signinProcessUrlOpenId = SecurityConstants.DEFAULT_SIGNIN_PROCESS_URL_OPENID;
  //标准的登录页面，如果其他项目没有配置则使用默认的登录配置
  private String signInUrl = SecurityConstants.DEFAULT_SIGNIN_PAGE_URL;
  //注册页面
  private String signUpUrl = SecurityConstants.DEFAULT_SIGNUP_PAGE_URL;
  //退出页面
  private String signOutUrl; //= SecurityConstants.DEFAULT_SIGNOUT_PAGE_URL;(不要默认值)
  //自定义需要放行认证的地址
  private String[] permitUrl = {};
  //登陆请求方式
  private AuthenticationResponseTypeEnum loginType = AuthenticationResponseTypeEnum.JSON;
  //默认记住账号密码时间(秒)
  private int rememberMeSeconds = SecurityConstants.DEFAULT_REMEMBERME_SECONDS;

  private SessionProperties session = new SessionProperties();

}
