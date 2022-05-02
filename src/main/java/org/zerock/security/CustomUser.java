package org.zerock.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.zerock.domain.MemberVO;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class CustomUser extends User {
    private static final long serialVersionUID = 1L;
    private MemberVO member;
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomUser(MemberVO member) {
        super(member.getUserId(), member.getPassword(),
                member.getAuthList()
                        .stream()
                        .map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
        this.member = member;
    }

}
