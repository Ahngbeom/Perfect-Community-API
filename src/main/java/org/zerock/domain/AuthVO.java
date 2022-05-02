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

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    public void setAuth(@NonNull String auth) {
        this.auth = auth;
    }
}
