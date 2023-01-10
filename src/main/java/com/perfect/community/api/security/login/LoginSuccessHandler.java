package com.perfect.community.api.security.login;

import com.perfect.community.api.utils.HttpServletCheck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger log = LogManager.getLogger();

    private static final HttpServletCheck servletCheck = new HttpServletCheck();

    // Reference: https://hungseong.tistory.com/60
    private void redirectToReferer(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String prevPage = (String) request.getSession().getAttribute("prevPage");

        log.info("Referer: " + request.getHeader("Referer"));
        log.info("Previous Page: " + prevPage);

        if (prevPage != null) {
            request.getSession().removeAttribute("prevPage");
            response.sendRedirect(prevPage);
        } else {
            response.sendRedirect("/");
        }
    }

    private void redirectToSavedRequest(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        RequestCache requestCache = new HttpSessionRequestCache(); // DefaultSavedRequest 객체를 세션에 저장
        SavedRequest savedRequest = requestCache.getRequest(request, response); // 요청 및 응답 데이터를 대입? 복사?

        log.info("Referer: " + request.getHeader("Referer"));
        log.info("Saved Request: " + savedRequest + (savedRequest != null ? "[" + savedRequest.getMethod() + "]" : ""));

        if (savedRequest != null/* && HttpMethod.GET.matches(savedRequest.getMethod())*/) {
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
//            response.setHeader("Location", savedRequest.getRedirectUrl());
//            response.setStatus(HttpServletResponse.SC_SEE_OTHER);
//            response.setHeader("Location", savedRequest.getRedirectUrl());
            response.sendRedirect(savedRequest.getRedirectUrl());
//            response.sendRedirect(prevPage);
        } else {
            response.sendRedirect("/");
        }

    }

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
        //        servletCheck.headerPrint(request);
        String xRequestedWithValue = request.getHeader("x-requested-with");
        if (xRequestedWithValue != null && xRequestedWithValue.equals("XMLHttpRequest")) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(authentication.getName());
        } else {
            redirectToReferer(request, response, authentication);
//            redirectToSavedRequest(request, response, authentication);
        }
    }

    /**
     * Called when a user has been successfully authenticated.
     *
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param chain          the {@link FilterChain} which can be used to proceed other filters in
     *                       the chain
     * @param authentication the <tt>Authentication</tt> object which was created during
     *                       the authentication process.
     * @since 5.2.0
     */
}
