package com.perfect.community.api.dto.user;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserAuthoritiesDTO {

    private String userId;
    private Set<String> authorities;

    @Builder
    public UserAuthoritiesDTO(String userId, Set<String> authorities) {
        this.userId = userId;
        this.authorities = authorities;
    }
}
