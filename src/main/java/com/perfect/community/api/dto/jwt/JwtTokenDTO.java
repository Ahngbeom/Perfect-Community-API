package com.perfect.community.api.dto.jwt;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class JwtTokenDTO {
    private String username;
    private String accessToken;
//    private Date accessTokenExpiration;
    private String refreshToken;
//    private Date refreshTokenExpiration;

    public JwtTokenDTO(String username, String accessToken, String refreshToken) {
        this.username = username;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
