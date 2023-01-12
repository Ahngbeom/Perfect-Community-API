package com.perfect.community.api.vo.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UserAuthorityEntity {

    private String userId;
    private String authority;

    @Builder
    public UserAuthorityEntity(String userId, String authority) {
        this.userId = userId;
        this.authority = authority;
    }

}
