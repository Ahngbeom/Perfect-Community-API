package com.perfect.community.api.security.detail;

import com.perfect.community.api.dto.user.UserDTO;
import com.perfect.community.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDTO userDTO = userService.getUserInfoWithAuthoritiesByUserId(userName);
        if (userDTO == null)
            throw new UsernameNotFoundException(userName);
        UserDetails userDetails = new CustomUserDetails(userDTO);
        log.info("{}", userDetails);
        return userDetails;
    }
}
