package org.zerock.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@ToString
public class AuthVO {

    private static final List<String> ROLE_LIST = new ArrayList<>(Arrays.asList("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"));

    private String userId;
    private String auth;

    public AuthVO(String userId, String auth) {
        this.userId = userId;
        this.auth = ROLE_LIST.contains(auth) ? auth : null;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

}
