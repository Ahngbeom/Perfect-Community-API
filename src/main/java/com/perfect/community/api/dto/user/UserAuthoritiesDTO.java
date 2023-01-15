package com.perfect.community.api.dto.user;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserAuthoritiesDTO {

    private String userId;
    private String authority;

    @Builder
    public UserAuthoritiesDTO(String userId, String authority) {
        this.userId = userId;
        this.authority = authority;
    }
}
