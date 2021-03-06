package com.harry.base.security.core.endpoint;

import static com.harry.base.security.core.constant.SecurityConstants.PRINCIPAL;

import com.harry.base.security.core.constant.SecurityConstants;
import com.harry.base.security.core.domain.BaseSecurityResponse;
import com.harry.base.security.core.properties.SecurityProperties;
import java.io.IOException;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BaseSecurityEndpoint {

  //拿到引发跳转的请求(HttpSessionRequestCache把当前的请求缓存到Session中)
  private final RequestCache requestCache = new HttpSessionRequestCache();

  private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  private final SecurityProperties securityProperties;


  //当需要身份认证时，跳转到这里
  @GetMapping(SecurityConstants.DEFAULT_LOGIN_PAGE)
  @ResponseStatus(code = HttpStatus.UNAUTHORIZED) //不是html请求时，返回401状态码
  public BaseSecurityResponse requireAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    //之前缓存的请求（可以拿到引发跳转的请求）
    SavedRequest savedRequest = requestCache.getRequest(request, response);
    if (savedRequest != null) {
      String targetUrl = savedRequest.getRedirectUrl();
      //是否以.html结尾，如果是则跳转到登录页面
      if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
        redirectStrategy
            .sendRedirect(request, response, securityProperties.getBrowser().getSignInUrl());
      }else {
        return new BaseSecurityResponse("访问的服务需要身份认证！");
      }
    }
    return new BaseSecurityResponse("服务认证！");
  }

  @GetMapping(PRINCIPAL)
  public Principal user(Principal principal) {
    return principal;
  }

}
