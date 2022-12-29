package com.perfect.community.api.dto.user;

import com.google.common.base.Preconditions;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private String userId;
    private String password;
    private String nickname;
    private boolean enabled;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private List<String> authorities;

    @Builder
    public UserDTO(String userId, String password, String nickname, boolean enabled, LocalDateTime regDate, LocalDateTime updateDate, List<String> authorities) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.enabled = enabled;
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return this.authorities != null
                ? "UserDTO{" +
                "userId='" + userId + '\'' +
                ", password=[PROTECTED]" +
                ", nickname='" + nickname + '\'' +
                ", enabled=" + enabled +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                ", authorities=" + authorities +
                '}'
                : "UserDTO{" +
                "userId='" + userId + '\'' +
                ", password=[PROTECTED]" +
                ", nickname='" + nickname + '\'' +
                ", enabled=" + enabled +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
