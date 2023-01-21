package com.perfect.community.api.dto.jwt;

import com.perfect.community.api.jwt.JwtTokenProvider;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

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
