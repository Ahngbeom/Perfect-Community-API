package org.zerock.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final Logger log = LogManager.getLogger();

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        if (authentication != null && authentication.getDetails() != null) {
            try {
                String prevPage = request.getHeader("Referer");
                if (prevPage == null)
                    prevPage = "/";
                request.getSession().invalidate();
                for (Cookie cookie : request.getCookies()) {
                    log.warn(cookie.getName());
                }

                Arrays.stream(request.getCookies()).anyMatch(cookie -> {
                    if (cookie.equals("JSESSIONID")) {
                        log.warn(cookie.getValue());
                        return true;
                    } else {
                        return false;
                    }
                });
                log.info("Logout Success - " + authentication.getName());
                log.info("Previous Page: " + prevPage);
                response.setStatus(HttpServletResponse.SC_OK);
                request.getSession().setAttribute("alertType", "Logout");
                request.getSession().setAttribute("alertStatus", "SUCCESS");
                response.sendRedirect(prevPage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
