package com.harry.base.security.core.endpoint;

import com.harry.base.security.core.constant.SecurityConstants;
import com.harry.base.security.core.domain.SocialUserInfo;
import com.harry.base.security.core.social.AppSignupUtils;
import com.harry.base.security.core.social.SocialConfig;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

@RestController
@AllArgsConstructor
@RequestMapping("social")
@ConditionalOnBean({SocialConfig.class})
public class AppSecurityEndPoint {

  private final ProviderSignInUtils providerSignInUtils;

  @Autowired(required = false)
  private final AppSignupUtils appSignupUtils;


  @GetMapping(SecurityConstants.DEFAULT_WEB_SOCIAL_INFO)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public SocialUserInfo getWebSocialUserInfo(HttpServletRequest request) {

    return this.buildFromSession(request, false);

  }

  @GetMapping(SecurityConstants.DEFAULT_APP_SOCIAL_INFO)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public SocialUserInfo getAppSocialUserInfo(HttpServletRequest request) {

    return this.buildFromSession(request, true);

  }

  private SocialUserInfo buildFromSession(HttpServletRequest request, boolean appSignup) {
    SocialUserInfo userInfo = new SocialUserInfo();

    Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));

    if (connection != null) {
      userInfo.setProviderId(connection.getKey().getProviderId());
      userInfo.setProviderUserId(connection.getKey().getProviderUserId());
      userInfo.setNickname(connection.getDisplayName());
      userInfo.setHeadimg(connection.getImageUrl());
      if (appSignup && appSignupUtils != null) {
        //将第三方绑定信息持久化
        appSignupUtils.saveConnectionData(new ServletWebRequest(request), connection.createData());
      }
    }
    return userInfo;
  }

}
