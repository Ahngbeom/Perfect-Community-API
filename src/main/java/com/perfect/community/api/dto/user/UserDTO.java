package com.perfect.community.api.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private String userId;
    private String password;
    private String nickname;
    private boolean enabled;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long regDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long updateDate;
    private Set<String> authorities;

    @Builder
    public UserDTO(String userId, String password, String nickname, boolean enabled, long regDate, long updateDate, Set<String> authorities) {
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
        return this.authorities != null && !this.authorities.isEmpty()
                ? "UserDTO{" +
                "userId='" + userId + '\'' +
                ", password=[PROTECTED]" +
                ", nickname='" + nickname + '\'' +
                ", enabled=" + enabled +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                ", authorities" + authorities +
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
