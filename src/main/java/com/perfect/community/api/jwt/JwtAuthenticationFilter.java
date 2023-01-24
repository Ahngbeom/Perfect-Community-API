package com.perfect.community.api.jwt;

import com.perfect.community.api.service.JwtService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisListCommands;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {

    @Value("${jwt.refresh-token-validity-in-seconds}")
    long refreshTokenValidity;

    public static String AUTHORIZATION_HEADER = "Authorization";
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private JwtService jwtService;

    private final RedisTemplate<String, String> redisTemplate;
    private final ValueOperations<String, String> valueOperations;
    private final ListOperations<String, String> listOperations;

    public JwtAuthenticationFilter(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.redisTemplate.setStringSerializer(new StringRedisSerializer());
        this.valueOperations = redisTemplate.opsForValue();
        this.listOperations = redisTemplate.opsForList();
    }

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

        log.warn("Authentication={}\n Access Token={}\n Refresh Token={}", SecurityContextHolder.getContext().getAuthentication(), accessToken, refreshToken);

        if (requestURI.equals("/api/login"))
            chain.doFilter(request, response);
        else if (StringUtils.hasText(accessToken)) {
            try {
                tokenProvider.validateAccessToken(accessToken);
                Authentication authentication = tokenProvider.getAuthentication(JwtTokenProvider.TOKEN_TYPE.ACCESS, accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("Stored '{}' authentication information in SecurityContext. (URI: {})", authentication.getName(), requestURI);
            } catch (ExpiredJwtException e) {
                reissueJWT(httpServletRequest, (HttpServletResponse) response, chain);
                return;
            } catch (Exception e) {
                log.error("{}:{}", e.getClass().getSimpleName(), e.getMessage());
                throw new JwtException(e.getMessage());
            }
        } else if (StringUtils.hasText(refreshToken)) {
            reissueJWT(httpServletRequest, (HttpServletResponse) response, chain);
            return;
        } else {
            log.warn("No valid JWT token.(URI: {})", requestURI);
        }
        chain.doFilter(request, response);
    }

    private void reissueJWT(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String refreshToken = jwtService.resolveRefreshToken(request);

        /* An exception is thrown by 'validateRefreshToken' if the refresh token validation fails. */
        tokenProvider.validateRefreshToken(refreshToken);

        /* Destroying existing tokens by reissuing tokens */
        listOperations.leftPush("jwt:destroyed", refreshToken);
        Objects.requireNonNull(listOperations.range("jwt:destroyed", 0, -1)).forEach(log::info);
//        redisTemplate.expire("discarded_tokens", Duration.ofDays(tokenProvider.getExpiration(JwtTokenProvider.TOKEN_TYPE.REFRESH, refreshToken)));

        Authentication authentication = tokenProvider.getAuthentication(JwtTokenProvider.TOKEN_TYPE.REFRESH, refreshToken);
        String accessToken = tokenProvider.createAccessToken(authentication);
        refreshToken = tokenProvider.createRefreshToken(authentication);
        tokenProvider.JwtToResponseHeaderAndCookie(response, accessToken, refreshToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("[SUCCESS] Reissued JWT");
        chain.doFilter(request, response);
    }

}
