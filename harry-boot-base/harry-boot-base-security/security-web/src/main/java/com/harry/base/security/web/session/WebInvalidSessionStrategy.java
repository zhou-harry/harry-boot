package com.harry.base.security.web.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.session.InvalidSessionStrategy;

/**
 * @author harry
 * @version 1.0
 * @title: WebInvalidSessionStrategy
 * @description: TODO
 * @date 2019/5/15 0:01
 */
public class WebInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {


    /**
     * @param invalidSessionUrl
     */
    public WebInvalidSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        onSessionInvalid(request, response);
    }


}
