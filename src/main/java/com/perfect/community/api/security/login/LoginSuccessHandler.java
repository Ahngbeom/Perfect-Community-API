package com.perfect.community.api.security.login;

import com.perfect.community.api.security.JSONConverterForAJAX;
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

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger log = LogManager.getLogger();

    /**
     * Called when a user has been successfully authenticated.
     *
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param authentication the <tt>Authentication</tt> object which was created during
     *                       the authentication process.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.sendRedirect("/");
    }

    //    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        RequestCache requestCache = new HttpSessionRequestCache(); // DefaultSavedRequest 객체를 세션에 저장
//        SavedRequest savedRequest = requestCache.getRequest(request, response); // 요청 및 응답 데이터를 대입? 복사?
//
//        String prevPage = (String) request.getSession().getAttribute("prevPage");
//        if (prevPage != null) {
//            request.getSession().removeAttribute("prevPage");
//            log.info("Previous Page: " + prevPage);
//        }
//
//        String url = request.getContextPath();
//        if (savedRequest != null) {
//            log.info("Saved Request: " + url);
//            url = savedRequest.getRedirectUrl();
//        } else if (prevPage != null && !prevPage.contains("/member/create")) {
//            url = prevPage;
////            response.sendRedirect(prevPage);
//        } else {
//            url = "/";
////            url = "/board/list";
//        }
//
//        log.warn(authentication);
//
//        response.sendRedirect(url);
////        Cookie[] cookies = request.getCookies();
////        if (cookies != null) {
////            for (Cookie c : cookies) {
////                if (c.getName().equals("remember-me")) {
////                    log.warn(c.getName() + ": " + c.getValue());
////                }
////            }
////        }
//
////        Map<String, Object> map = new HashMap<>();
////        map.put("status", "SUCCESS");
////        map.put("redirectURL", url);
////        map.put("userID", authentication != null ? authentication.getName() : null);
////        JSONConverter(response, authentication, map);
//
//    }
}
