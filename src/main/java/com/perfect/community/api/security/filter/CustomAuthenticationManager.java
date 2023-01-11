package com.perfect.community.api.security.filter;

import com.perfect.community.api.security.detail.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final CustomUserDetailService userDetailsService;

    private final BCryptPasswordEncoder passwordEncoder;
    /**
     * Attempts to authenticate the passed {@link Authentication} object, returning a
     * fully populated <code>Authentication</code> object (including granted authorities)
     * if successful.
     * <p>
     * An <code>AuthenticationManager</code> must honour the following contract concerning
     * exceptions:
     * <ul>
     * <li>A {@link DisabledException} must be thrown if an account is disabled and the
     * <code>AuthenticationManager</code> can test for this state.</li>
     * <li>A {@link LockedException} must be thrown if an account is locked and the
     * <code>AuthenticationManager</code> can test for account locking.</li>
     * <li>A {@link BadCredentialsException} must be thrown if incorrect credentials are
     * presented. Whilst the above exceptions are optional, an
     * <code>AuthenticationManager</code> must <B>always</B> test credentials.</li>
     * </ul>
     * Exceptions should be tested for and if applicable thrown in the order expressed
     * above (i.e. if an account is disabled or locked, the authentication request is
     * immediately rejected and the credentials testing process is not performed). This
     * prevents credentials being tested against disabled or locked accounts.
     *
     * @param authentication the authentication request object
     * @return a fully authenticated object including credentials
     * @throws AuthenticationException if authentication fails
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) authentication.getPrincipal());
        if (userDetails == null)
            throw new UsernameNotFoundException((String) authentication.getPrincipal());
        if (!userDetails.isAccountNonLocked())
            throw new LockedException("Account is locked.");
        if (!userDetails.isEnabled())
            throw new DisabledException("Account is disabled.");
        if (!passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword()))
            throw new BadCredentialsException("Bad Credentials.");
        log.info("UserDetails: " + userDetails);
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
