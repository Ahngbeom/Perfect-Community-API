package com.perfect.community.api.entity.user;

import com.perfect.community.api.dto.user.UserDTO;
import com.perfect.community.api.entity.authorities.AuthoritiesEntity;
import lombok.*;

import java.time.LocalDateTime;
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

    public UserDTO toDTO() {
        return UserDTO.builder()
                .userId(this.userId)
                .password(this.password)
                .nickname(this.nickname)
                .enabled(this.enabled)
                .regDate(this.regDate)
                .updateDate(this.updateDate)
                .authority(this.authority)
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
        return authority != null
                ? "UserEntity{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + nickname + '\'' +
                ", enabled=" + enabled +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                ", authority=" + authority +
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
