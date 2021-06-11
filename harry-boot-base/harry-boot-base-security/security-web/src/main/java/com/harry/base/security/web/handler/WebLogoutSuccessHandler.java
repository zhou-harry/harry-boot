package com.harry.base.security.web.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harry.base.security.core.domain.BaseSecurityResponse;
import com.harry.base.security.core.properties.SecurityProperties;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author harry
 * @version 1.0
 * @title: WebLogoutSuccessHandler
 * @description: 退出系统Handler
 * @date 2019/5/16 23:02
 */
public class WebLogoutSuccessHandler implements LogoutSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private ObjectMapper objectMapper = new ObjectMapper();
    private SecurityProperties properties;

    public WebLogoutSuccessHandler(SecurityProperties properties) {
        this.properties = properties;
    }


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User principal = (User)authentication.getPrincipal();

        String signOutUrl = properties.getBrowser().getSignOutUrl();
        if (StringUtils.isBlank(signOutUrl)) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(new BaseSecurityResponse("退出成功")));
        } else {
            response.sendRedirect(signOutUrl);
        }
    }

}
