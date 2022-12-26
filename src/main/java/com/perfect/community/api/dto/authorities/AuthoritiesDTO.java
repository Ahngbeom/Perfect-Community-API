package com.perfect.community.api.dto.authorities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AuthoritiesDTO implements GrantedAuthority {

    private String authority;

    @Builder
    public AuthoritiesDTO(String authority) {
        this.authority = authority;
    }
}
