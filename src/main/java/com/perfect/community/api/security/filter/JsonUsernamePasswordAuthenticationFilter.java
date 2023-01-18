package com.perfect.community.api.security.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfect.community.api.jwt.JwtAuthenticationFilter;
import com.perfect.community.api.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StreamUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_JSON_USERNAME_KEY = "username";

    public static final String SPRING_SECURITY_JSON_PASSWORD_KEY = "password";

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/api/login",
            "POST");

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    /**
     * Creates a new instance with a {@link RequestMatcher} and an
     * {@link AuthenticationManager}
     *
     * @param authenticationManager the {@link AuthenticationManager} used to authenticate
     *                              an {@link Authentication} object. Cannot be null.
     * @param jwtTokenProvider      For add|set bearer jwt token to header
     * @param objectMapper          response with JSON data
     */
    protected JsonUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationSuccessHandler authenticationSuccessHandler, AuthenticationFailureHandler authenticationFailureHandler, JwtTokenProvider jwtTokenProvider, ObjectMapper objectMapper) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
        setAuthenticationSuccessHandler(authenticationSuccessHandler);
        setAuthenticationFailureHandler(authenticationFailureHandler);
        this.jwtTokenProvider = jwtTokenProvider;
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        // 이미 인증된 유저의 로그인 시도 차단      
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            throw new AuthenticationServiceException("Already authenticated - " + authentication.getName());
        }
        // 이미 Bearer 토큰을 가지고 있는 유저의 로그인 시도 차단
        if (request.getHeader("Authorization") != null) {
            throw new AuthenticationServiceException("Already have a bearer token - " + request.getHeader("Authorization"));
//            if (!jwtTokenProvider.validateToken(request.getHeader("Authorization").substring("Bearer ".length()))) {
//
//            }
        }
        // POST 이외의 다른 HTTP 요청 메소드 거부
        if (!HttpMethod.POST.matches(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported - " + request.getMethod());
        }
        // application/json 이외에 ContentType 거부
        if (!request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            throw new AuthenticationServiceException("Authentication content type not supported - " + request.getContentType());
        }

        JsonNode requestJSON = objectMapper.readTree(StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8));
        String username = requestJSON.get(SPRING_SECURITY_JSON_USERNAME_KEY).asText();
        String password = requestJSON.get(SPRING_SECURITY_JSON_PASSWORD_KEY).asText();

        username = (username != null) ? username : "";
        username = username.trim();
        password = (password != null) ? password : "";

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        setDetails(request, authenticationToken);
        authentication = this.getAuthenticationManager().authenticate(authenticationToken);
        logger.info("[Authentication Success]\n" + authentication);

        return authentication;
    }

    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authenticationToken) {
        authenticationToken.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

}
