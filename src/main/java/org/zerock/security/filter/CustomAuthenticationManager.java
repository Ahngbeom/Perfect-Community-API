package org.zerock.security.filter;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.zerock.security.detail.CustomUserDetailService;

@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final CustomUserDetailService userDetailsService;

    private static final Logger log = LogManager.getLogger();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("[CustomAuthenticationManager] authenticate");
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) authentication.getPrincipal());
        log.warn(userDetails);
        if (userDetails == null)
            throw new UsernameNotFoundException((String) authentication.getPrincipal());
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }
}
