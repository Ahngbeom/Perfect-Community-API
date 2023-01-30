package com.perfect.community.api.security.jwt;

import com.perfect.community.api.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtSecurityContextHolderFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final JwtTokenProvider tokenProvider;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        String accessToken = bearerToken != null ? bearerToken.substring("Bearer ".length()) : null;
        String refreshToken = jwtService.resolveRefreshToken(request);

        Authentication authentication = null;

        if (accessToken != null && tokenProvider.validateToken(accessToken)) {
            authentication = tokenProvider.getAuthentication(accessToken);
        } else if (refreshToken != null && tokenProvider.validateToken(refreshToken)){
            authentication = tokenProvider.getAuthentication(refreshToken);
        }

        if (authentication != null) {
            try {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("Security Context Holder Authentication: {}", SecurityContextHolder.getContext().getAuthentication());
                filterChain.doFilter(request, response);
            }
            finally {
                SecurityContextHolder.clearContext();
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
