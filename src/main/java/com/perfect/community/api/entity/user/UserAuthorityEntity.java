package com.perfect.community.api.entity.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class UserAuthorityEntity {

    private String userId;
    private List<String> authorities;

    @Builder
    public UserAuthorityEntity(String userId, List<String> authorities) {
        this.userId = userId;
        this.authorities = authorities;
    }

}
