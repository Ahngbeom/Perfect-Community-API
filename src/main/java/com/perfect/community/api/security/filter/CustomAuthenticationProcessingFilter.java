package com.perfect.community.api.security.filter;

import com.perfect.community.api.security.login.LoginFailureHandler;
import com.perfect.community.api.security.login.LoginSuccessHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class CustomAuthenticationProcessingFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger log = LogManager.getLogger();

//    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login",
//            "POST");

    private final CustomAuthenticationManager authenticationManager;



    private boolean postOnly = true;

    public CustomAuthenticationProcessingFilter(CustomAuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
        super.setAuthenticationManager(this.authenticationManager);
        super.setAuthenticationFailureHandler(new LoginFailureHandler());
        super.setAuthenticationSuccessHandler(new LoginSuccessHandler());
    }

    // defaultFilterProcessesUrl이 호출되면 CustomAuthenticationProcessingFilter 수행

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("[CustomAuthenticationProcessingFilter] attemptAuthentication");

        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        // 요청받은 username, password 파라미터를 받아온다.
        String username = request.getParameter("username");
        username = (username != null) ? username : "";
        username = username.trim();
        String password = request.getParameter("password");
        password = (password != null) ? password : "";

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        // Allow subclasses to set the "details" property
//        super.setDetails(request, authRequest);
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));

        Authentication authentication = this.getAuthenticationManager().authenticate(authRequest);

        Enumeration<String> enumeration = request.getSession().getAttributeNames();

        log.warn("Authentication: " + authentication);
        log.warn("Cookies: " + enumeration.nextElement());
//        request.getSession().setAttribute("principal", authentication);

        // UsernamePasswordAuthenticationToken에 username, password를 담아 실질적인 인증 처리 매니저를 호출
        return authentication;
    }


}
