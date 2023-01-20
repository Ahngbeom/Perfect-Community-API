package com.perfect.community.api.security.authentication;

import com.perfect.community.api.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("Logout Success - {}", authentication);
        response.setHeader("Authorization", null);
//        Cookie cookie = new Cookie("refresh_token", null);
//        cookie.setMaxAge(-1);
//        cookie.setPath("/");
//        cookie.setHttpOnly(true);
//        response.addCookie(cookie);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("OK");
//        request.getSession().invalidate();
//        String prevPage = request.getHeader("Referer");
//        if (prevPage == null)
//            prevPage = "/";
//        response.sendRedirect(prevPage);
    }

}
