package com.perfect.community.api.jwt;

import com.perfect.community.api.dto.jwt.TokenDTO;
import com.perfect.community.api.service.JwtService;
import com.perfect.community.api.service.redis.RedisService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {

    @Value("${jwt.refresh-token-validity-in-seconds}")
    long refreshTokenValidity;

    public static String AUTHORIZATION_HEADER = "Authorization";
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private RedisService redisService;

//    private final RedisTemplate<String, String> redisTemplate;
//    private final ValueOperations<String, String> valueOperations;
//    private final ListOperations<String, String> listOperations;

    /**
     * The <code>doFilter</code> method of the Filter is called by the
     * container each time a request/response pair is passed through the
     * chain due to a client request for a resource at the end of the chain.
     * The FilterChain passed in to this method allows the Filter to pass
     * on the request and response to the next entity in the chain.
     *
     * <p>A typical implementation of this method would follow the following
     * pattern:
     * <ol>
     * <li>Examine the request
     * <li>Optionally wrap the request object with a custom implementation to
     * filter content or headers for input filtering
     * <li>Optionally wrap the response object with a custom implementation to
     * filter content or headers for output filtering
     * <li>
     * <ul>
     * <li><strong>Either</strong> invoke the next entity in the chain
     * using the FilterChain object
     * (<code>chain.doFilter()</code>),
     * <li><strong>or</strong> not pass on the request/response pair to
     * the next entity in the filter chain to
     * block the request processing
     * </ul>
     * <li>Directly set headers on the response after invocation of the
     * next entity in the filter chain.
     * </ol>
     *
     * @param request  the <code>ServletRequest</code> object contains the client's request
     * @param response the <code>ServletResponse</code> object contains the filter's response
     * @param chain    the <code>FilterChain</code> for invoking the next filter or the resource
     * @throws IOException      if an I/O related error has occurred during the processing
     * @throws ServletException if an exception occurs that interferes with the
     *                          filter's normal operation
     * @see UnavailableException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String accessToken = jwtService.resolveAccessToken(httpServletRequest);
        String refreshToken = jwtService.resolveRefreshToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();

        log.warn("Authentication by SecurityContextHolder={}\n Access Token={}\n Refresh Token={}", SecurityContextHolder.getContext().getAuthentication(), accessToken, refreshToken);

        if (requestURI.equals("/api/login") || requestURI.equals("/api/jwt/reissue"))
            chain.doFilter(request, response);
        else {
            if (StringUtils.hasText(accessToken)) {
                try {
                    /* Access token validation */
                    tokenProvider.validateToken(accessToken);

                    Authentication authentication = tokenProvider.getAuthentication(accessToken);

                    /* Compared to Redis tokens.  */
                    redisService.validateAccessTokenByUsername(authentication.getName(), accessToken);
                    
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.info("Stored '{}' authentication information in SecurityContext. (URI: {})", authentication.getName(), requestURI);
                } catch (ExpiredJwtException e) {
                    reissueJWT(httpServletRequest, (HttpServletResponse) response, chain, refreshToken);
                    return;
                } catch (Exception e) {
                    log.error("{}:{}", e.getClass().getSimpleName(), e.getMessage());
                    throw new JwtException(e.getMessage());
                }
            } else if (StringUtils.hasText(refreshToken)) {
                reissueJWT(httpServletRequest, (HttpServletResponse) response, chain, refreshToken);
                return;
            } else {
                log.warn("No valid JWT token.(URI: {})", requestURI);
            }
            chain.doFilter(request, response);
        }
    }

    private void reissueJWT(HttpServletRequest request, HttpServletResponse response, FilterChain chain, String refreshToken) throws ServletException, IOException {
//        String accessToken = request.getHeader(AUTHORIZATION_HEADER).substring("Bearer ".length());
        try {
            /* An exception is thrown by 'validateToken' if the refresh token validation fails. */
            tokenProvider.validateToken(refreshToken);

            /* Compared to Redis tokens.  */
            Authentication authentication = tokenProvider.getAuthentication(refreshToken);
            redisService.validateRefreshTokenByUsername(authentication.getName(), refreshToken);

            /* Reissue tokens */
            TokenDTO tokenDTO = tokenProvider.generateToken(authentication);

            /* Replacing with reissued tokens */
            redisService.putJWT(tokenDTO);

            /* Reissued JWT to response */
            tokenProvider.JwtToResponseHeaderAndCookie(response, tokenDTO);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("[SUCCESS] Reissued JWT");
            chain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            throw new JwtException("Refresh token has expired. Please sign in again.");
        }
    }

}
