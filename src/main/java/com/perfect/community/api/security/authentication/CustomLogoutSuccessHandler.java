package com.perfect.community.api.security.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("Logout Success - " + authentication);
        request.getSession().invalidate();
        String prevPage = request.getHeader("Referer");
        if (prevPage == null)
            prevPage = "/";
        response.sendRedirect(prevPage);
    }

}
