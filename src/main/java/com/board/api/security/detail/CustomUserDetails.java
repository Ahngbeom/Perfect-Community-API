package com.board.api.security.detail;

import lombok.Getter;
import org.springframework.security.core.userdetails.User;
import com.board.api.dto.user.UserDTO;

@Getter
public class CustomUserDetails extends User {
    private static final long serialVersionUID = 1L;
    private final UserDTO member;

//    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, authorities);
//    }
//
//    public CustomUserDetails(String username, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, enabled, enabled, enabled, enabled, authorities);
//    }

    public CustomUserDetails(UserDTO member) {
        super(member.getUserId(), member.getPassword(),
                member.isEnabled(), true,
                true, true,
                member.getAuthList());
        this.member = member;
    }

}
