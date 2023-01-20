package com.perfect.community.api.jwt;

import com.perfect.community.api.dto.jwt.JwtTokenDTO;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {

    public static String AUTHORIZATION_HEADER = "Authorization";
    private final JwtTokenProvider tokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.tokenProvider = jwtTokenProvider;
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
        String accessToken = resolveAccessToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();

        log.warn("Access Token={}", accessToken);

        if (StringUtils.hasText(accessToken)) {
            try {
                tokenProvider.validateAccessToken(accessToken);
            } catch (ExpiredJwtException e) {
              String refreshToken = resolveRefreshToken(httpServletRequest);
              if (tokenProvider.validateRefreshToken(refreshToken)) {
                  HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                  Authentication authentication = tokenProvider.getAuthentication(refreshToken);
                  accessToken = tokenProvider.createAccessToken(authentication);
                  refreshToken = tokenProvider.createRefreshToken(authentication);
                  JwtTokenDTO tokenDTO = new JwtTokenDTO(authentication.getName(), accessToken, refreshToken);
                  tokenProvider.JwtToResponseHeaderAndCookie(httpServletResponse, tokenDTO);
                  log.info("Reissue JWT");
                  chain.doFilter(request, httpServletResponse);
              }
            } catch (Exception e) {
                log.error(e.getMessage());
                SecurityContextHolder.clearContext();
                throw new JwtException(e.getMessage());
            }
            Authentication authentication = tokenProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("Stored '{}' authentication information in SecurityContext. (URI: {})", authentication.getName(), requestURI);
        } else {
            log.warn("No valid JWT token.\nURI: {}", requestURI);
        }
        chain.doFilter(request, response);
    }

    private String resolveAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }

    private String resolveRefreshToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refresh-token")) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
