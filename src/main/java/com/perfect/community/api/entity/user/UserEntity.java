package com.perfect.community.api.entity.user;

import com.perfect.community.api.dto.user.UserDTO;
import com.perfect.community.api.entity.authorities.AuthoritiesEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class UserEntity {

    private String userId;
    private String password;
    private String nickname;
    private boolean enabled;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private List<AuthoritiesEntity> authorities;

    @Builder
    public UserEntity(String userId, String password, String nickname, boolean enabled, LocalDateTime regDate, LocalDateTime updateDate, List<AuthoritiesEntity> authorities) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.enabled = enabled;
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.authorities = authorities;
    }

    public UserDTO toDTO() {
        return UserDTO.builder()
                .userId(this.userId)
                .password(this.password)
                .nickname(this.nickname)
                .enabled(this.enabled)
                .regDate(this.regDate)
                .updateDate(this.updateDate)
                .authorities(
                        this.authorities != null
                                ? this.authorities.stream().map(AuthoritiesEntity::getAuthority).collect(Collectors.toList())
                                : null)
                .build();
    }

    public static UserDTO toDTO(UserEntity entity) {
        return UserDTO.builder()
                .userId(entity.userId)
                .password(entity.password)
                .nickname(entity.nickname)
                .enabled(entity.enabled)
                .regDate(entity.regDate)
                .updateDate(entity.updateDate)
                .build();
    }

    @Override
    public String toString() {
        return authorities != null && authorities.size() > 0
                ? "UserEntity{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + nickname + '\'' +
                ", enabled=" + enabled +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                ", authorities=" + authorities +
                '}'
                :
                "UserEntity{" +
                        "userId='" + userId + '\'' +
                        ", password='" + password + '\'' +
                        ", userName='" + nickname + '\'' +
                        ", enabled=" + enabled +
                        ", regDate=" + regDate +
                        ", updateDate=" + updateDate +
                        '}';
    }
}