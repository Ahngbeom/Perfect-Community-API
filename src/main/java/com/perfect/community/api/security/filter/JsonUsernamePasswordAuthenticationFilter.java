package com.perfect.community.api.security.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class JsonUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    public static final String SPRING_SECURITY_JSON_USERNAME_KEY = "username";

    public static final String SPRING_SECURITY_JSON_PASSWORD_KEY = "password";

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login",
            "POST");

    private boolean postOnly = true;

    private final ObjectMapper objectMapper;

    public JsonUsernamePasswordAuthenticationFilter(ObjectMapper objectMapper) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
        this.objectMapper = objectMapper;
    }

    /**
     * Creates a new instance with a {@link RequestMatcher} and an
     * {@link AuthenticationManager}
     *
     * @param authenticationManager                the {@link AuthenticationManager} used to authenticate
     *                                             an {@link Authentication} object. Cannot be null.
     * @param objectMapper                         response with JSON data
     */
    protected JsonUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationSuccessHandler authenticationSuccessHandler, AuthenticationFailureHandler authenticationFailureHandler, ObjectMapper objectMapper) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
        setAuthenticationSuccessHandler(authenticationSuccessHandler);
        setAuthenticationFailureHandler(authenticationFailureHandler);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        log.info("[CustomAuthenticationProcessingFilter] attemptAuthentication");

        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        if (!request.getContentType().equals("application/json")) {
            throw new AuthenticationServiceException("Authentication content type not supported: " + request.getContentType());
        }

        JsonNode requestJSON = objectMapper.readTree(StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8));
        String username = requestJSON.get(SPRING_SECURITY_JSON_USERNAME_KEY).asText();
        String password = requestJSON.get(SPRING_SECURITY_JSON_PASSWORD_KEY).asText();

        username = (username != null) ? username : "";
        username = username.trim();
        password = (password != null) ? password : "";

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        // Allow subclasses to set the "details" property
//        super.setDetails(request, authRequest);
        setDetails(request, authRequest);

        Authentication authentication = this.getAuthenticationManager().authenticate(authRequest);

//        Enumeration<String> enumeration = request.getSession().getAttributeNames();

        log.warn("Authentication: " + authentication);
//        log.warn("Cookies: " + enumeration.nextElement());
//        request.getSession().setAttribute("principal", authentication);

        return authentication;
    }

    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

}
