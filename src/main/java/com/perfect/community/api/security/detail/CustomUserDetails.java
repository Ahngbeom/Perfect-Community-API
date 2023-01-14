package com.perfect.community.api.security.detail;

import com.perfect.community.api.dto.user.UserDTO;
import com.perfect.community.api.vo.user.UserVO;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Getter
public class CustomUserDetails extends User {
    private static final long serialVersionUID = 1L;
    private final UserDTO user;

    public CustomUserDetails(UserDTO user) {
        super(user.getUserId(), user.getPassword(),
                user.isEnabled(), true,
                true, true,
                Collections.singleton(new SimpleGrantedAuthority(user.getAuthority())));
        this.user = user;
    }

}
