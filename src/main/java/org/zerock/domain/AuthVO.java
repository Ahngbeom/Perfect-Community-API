package org.zerock.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

@Getter
@ToString
@RequiredArgsConstructor
public class AuthVO {

    @NonNull
    private String userId;
    @NonNull
    private String auth;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}