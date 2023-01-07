package com.perfect.community.api.security.detail;

import com.perfect.community.api.dto.user.UserDTO;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;
import java.util.stream.Collectors;

@Getter
public class CustomUserDetails extends User {
    private static final long serialVersionUID = 1L;
    private final UserDTO user;

//    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, authorities);
//    }
//
//    public CustomUserDetails(String username, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, enabled, enabled, enabled, enabled, authorities);
//    }

    public CustomUserDetails(UserDTO user) {
        super(user.getUserId(), user.getPassword(),
                user.isEnabled(), true,
                true, true,
                Collections.singleton(new SimpleGrantedAuthority(user.getAuthority())));
        this.user = user;
    }

}
