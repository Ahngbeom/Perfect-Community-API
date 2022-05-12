package org.zerock.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger log = LogManager.getLogger();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        List<String> roleList = new ArrayList<>();
        authentication.getAuthorities().forEach(grantedAuthority -> roleList.add(grantedAuthority.getAuthority()));

        log.warn("Login Success - ROLE NAMES: " + roleList);

        RequestCache requestCache = new HttpSessionRequestCache(); // DefaultSavedRequest 객체를 세션에 저장
        SavedRequest savedRequest = requestCache.getRequest(request, response); // 요청 및 응답 데이터를 대입? 복사?

        String prevPage = (String) request.getSession().getAttribute("prevPage");
        if (prevPage != null) {
            request.getSession().removeAttribute("prevPage");
            log.info("Previous Page: " + prevPage);
        }

        String url = request.getContextPath();
        if (savedRequest != null) {
            log.info("Saved Request: " + url);
            url = savedRequest.getRedirectUrl();
        } else if (prevPage != null) {
            url = prevPage;
        }
        request.getSession().setAttribute("type", "Login");
        request.getSession().setAttribute("state", "SUCCESS");
        response.sendRedirect(url);
    }
}
