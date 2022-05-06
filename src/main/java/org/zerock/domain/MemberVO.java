package org.zerock.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
public class MemberVO {

    @NonNull
    private String userId;
    @NonNull
    private String password;
    @NonNull
    private String userName;

    private boolean enabled;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    private List<AuthVO> authList;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public void setAuthList(List<AuthVO> authList) {
        this.authList = authList;
    }
}
