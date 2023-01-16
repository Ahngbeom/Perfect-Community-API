package com.perfect.community.api.security.authentication;

import com.perfect.community.api.jwt.JwtAuthenticationFilter;
import com.perfect.community.api.jwt.JwtTokenProvider;
import com.perfect.community.api.mapper.user.LoginHistoryMapper;
import com.perfect.community.api.utils.HttpServletCheck;
import com.perfect.community.api.vo.LoginHistoryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final LoginHistoryMapper loginHistoryMapper;

    private final HttpServletCheck servletCheck = new HttpServletCheck();

    /**
     * Called when a user has been successfully authenticated.
     *
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param authentication the <tt>Authentication</tt> object which was created during
     *                       the authentication process.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
//        servletCheck.headerPrint(request);
        String xRequestedWithValue = request.getHeader("x-requested-with");
        String contentType = request.getContentType();
        saveLoginHistory(request, authentication);
        if ((contentType != null && contentType.equals("application/json")) || (xRequestedWithValue != null && xRequestedWithValue.equals("XMLHttpRequest"))) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(authentication.getName());
        } else {
            redirectToReferer(request, response, authentication);
//            redirectToSavedRequest(request, response, authentication);
        }
    }

    private void saveLoginHistory(HttpServletRequest request, Authentication authentication) {
        String loggedInClientIp = request.getHeader("X-Forwarded-For");
        if (loggedInClientIp == null)
            loggedInClientIp = request.getRemoteAddr();
        LoginHistoryVO loginHistoryVO = LoginHistoryVO.builder()
                .user_id(authentication.getName())
                .ip_address(loggedInClientIp)
                .user_agent(request.getHeader("user-agent"))
                .build();
        log.info("Save authentication log = {}",
                loginHistoryMapper.saveLoggedInLog(loginHistoryVO) == 1 ? "OK" : "Failed");
    }

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


}
