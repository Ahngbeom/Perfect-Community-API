package com.perfect.community.api.security.detail;

import com.perfect.community.api.dto.user.UserDTO;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.stream.Collectors;


@Slf4j
@Getter
public class CustomUserDetails extends User {
    private static final long serialVersionUID = 1L;
//    private final UserDTO user;

    public CustomUserDetails(UserDTO user) {
        super(user.getUserId(), user.getPassword(),
                user.isEnabled(), true,
                true, true,
                user.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
//        this.user = user;
    }

}
