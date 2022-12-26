package com.perfect.community.api.dto.user;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private String userId;
    private String password;
    private String userName;
    private boolean enabled;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private List<String> authorities;

    @Builder
    public UserDTO(String userId, String password, String userName, boolean enabled, LocalDateTime regDate, LocalDateTime updateDate, List<String> authorities) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
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
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", enabled=" + enabled +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                ", authorities=" + authorities +
                '}'
                : "UserDTO{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", enabled=" + enabled +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
