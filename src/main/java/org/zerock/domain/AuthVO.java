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
    private String userName;
    @NonNull
    private String auth;

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    public void setAuth(@NonNull String auth) {
        this.auth = auth;
    }
}
