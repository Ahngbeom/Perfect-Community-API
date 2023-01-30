package com.perfect.community.api.security.jwt;

import com.perfect.community.api.dto.jwt.TokenDTO;
import com.perfect.community.api.service.JwtService;
import com.perfect.community.api.service.redis.RedisService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
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

        logger.warn("Authentication by SecurityContextHolder="+ SecurityContextHolder.getContext().getAuthentication()
                + "\n Access Token=" + accessToken
                + "\n Refresh Token=" + refreshToken);


        if (requestURI.equals("/api/login") || requestURI.equals("/api/jwt/reissue"))
            chain.doFilter(request, response);
        else {
            /* Access token validation */
            if (StringUtils.hasText(accessToken) && tokenProvider.validateToken(accessToken)) {
                try {
                    Authentication authentication = tokenProvider.getAuthentication(accessToken);

                    /* Compared to Redis tokens.  */
                    if (!redisService.validateAccessTokenByUsername(authentication.getName(), accessToken))
                        throw new JwtException("Invalid JWT.");

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    logger.info("Stored '" + authentication.getName() + "' authentication information in SecurityContext. (URI: " + requestURI + ")");
                } catch (ExpiredJwtException e) {
                    reissueJWT(httpServletRequest, (HttpServletResponse) response, chain, refreshToken);
                } catch (Exception e) {
                    logger.error(e.getClass().getSimpleName() + " - " + e.getMessage());
                    throw new JwtException(e.getMessage());
                }
            } else if (StringUtils.hasText(refreshToken) && tokenProvider.validateToken(refreshToken)) {
                reissueJWT(httpServletRequest, (HttpServletResponse) response, chain, refreshToken);
            } else {
                logger.warn("No valid JWT token.(URI: " + requestURI + ")");
            }
            chain.doFilter(request, response);
        }
    }

    private void reissueJWT(HttpServletRequest request, HttpServletResponse response, FilterChain chain, String refreshToken) throws ServletException, IOException {
        try {
            /* An exception is thrown by 'validateToken' if the refresh token validation fails. */
            if (!tokenProvider.validateToken(refreshToken))
                throw new JwtException("Invalid JWT.");

            /* Compared to Redis tokens.  */
            Authentication authentication = tokenProvider.getAuthentication(refreshToken);
            if (!redisService.validateRefreshTokenByUsername(authentication.getName(), refreshToken))
                throw new JwtException("Invalid JWT.");

            /* Reissue tokens */
            TokenDTO tokenDTO = tokenProvider.generateToken(authentication);

            /* Replacing with reissued tokens */
            redisService.putJWT(tokenDTO);

            /* Reissued JWT to response */
            tokenProvider.JwtToResponseHeaderAndCookie(response, tokenDTO);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.info("[SUCCESS] Reissued JWT");
        } catch (ExpiredJwtException e) {
            throw new JwtException("Refresh token has expired. Please sign in again.");
        }
    }

}
