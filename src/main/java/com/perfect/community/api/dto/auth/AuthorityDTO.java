package com.perfect.community.api.dto.auth;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthorityDTO implements GrantedAuthority {

    private String userId;
    private String authority;

}
