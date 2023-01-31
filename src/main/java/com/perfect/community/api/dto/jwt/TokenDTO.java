package com.perfect.community.api.dto.jwt;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TokenDTO {

    private String username;
    private String accessToken;
    private Date accessTokenExpiration;
    private String refreshToken;
    private Date refreshTokenExpiration;

    @Builder
    public TokenDTO(String username, String accessToken, Date accessTokenExpiration, String refreshToken, Date refreshTokenExpiration) {
        this.username = username;
        this.accessToken = accessToken;
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }
}
