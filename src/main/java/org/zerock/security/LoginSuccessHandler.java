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

        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        String prevPage = (String) request.getSession().getAttribute("prevPage");
        if (prevPage != null) {
            request.getSession().removeAttribute("prevPage");
        }

        String url = request.getContextPath();
        if (savedRequest != null) {
            url = savedRequest.getRedirectUrl();
        } else if (prevPage != null) {
            url = prevPage;
        }



//        if (roleList.contains("ROLE_ADMIN")) {
//            response.sendRedirect("/admin");
//            return;
//        }
//        else if (roleList.contains("ROLE_MEMBER")) {
//            response.sendRedirect("/member");
//            return;
//        }
//        request.getSession().setAttribute("type", "Login");
//        request.getSession().setAttribute("state", "SUCCESS");
        response.sendRedirect("/");
    }
}
