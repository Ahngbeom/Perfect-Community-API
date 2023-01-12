package com.perfect.community.api.vo.user;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserEntity {

    private String userId;
    private String password;
    private String nickname;
    private boolean enabled;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private String authority;

    @Builder
    public UserEntity(String userId, String password, String nickname, boolean enabled, LocalDateTime regDate, LocalDateTime updateDate, String authority) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.enabled = enabled;
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.authority = authority;
    }

    @Override
    public String toString() {
        return authority != null
                ? "UserEntity{" +
                "userId='" + userId + '\'' +
                ", password=[PROTECTED]" +
                ", userName='" + nickname + '\'' +
                ", enabled=" + enabled +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                ", authority=" + authority +
                '}'
                :
                "UserEntity{" +
                        "userId='" + userId + '\'' +
                        ", password=[PROTECTED]" +
                        ", userName='" + nickname + '\'' +
                        ", enabled=" + enabled +
                        ", regDate=" + regDate +
                        ", updateDate=" + updateDate +
                        '}';
    }
}
