package com.perfect.community.api.dto.user;

import lombok.*;

import java.time.LocalDateTime;

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
    private String authority;

    @Builder
    public UserDTO(String userId, String password, String nickname, boolean enabled, LocalDateTime regDate, LocalDateTime updateDate, String authority) {
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
        return this.authority != null && !this.authority.isEmpty()
                ? "UserDTO{" +
                "userId='" + userId + '\'' +
                ", password=[PROTECTED]" +
                ", nickname='" + nickname + '\'' +
                ", enabled=" + enabled +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                ", authority=" + authority +
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
