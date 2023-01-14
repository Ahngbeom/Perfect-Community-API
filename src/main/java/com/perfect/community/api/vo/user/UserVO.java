package com.perfect.community.api.vo.user;

import com.perfect.community.api.dto.user.UserDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class UserVO {

    private final String user_id;
    private final String password;
    private final String nickname;
    private final boolean enabled;
    private final LocalDateTime reg_date;
    private final LocalDateTime update_date;

    @Builder
    public UserVO(String user_id, String password, String nickname, boolean enabled, LocalDateTime reg_date, LocalDateTime update_date) {
        this.user_id = user_id;
        this.password = password;
        this.nickname = nickname;
        this.enabled = enabled;
        this.reg_date = reg_date;
        this.update_date = update_date;
    }

    public UserVO(UserDTO userDTO) {
        this.user_id = userDTO.getUserId();
        this.password = userDTO.getPassword();
        this.nickname = userDTO.getNickname();
        this.enabled = userDTO.isEnabled();
        this.reg_date = userDTO.getRegDate();
        this.update_date = userDTO.getUpdateDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVO userVO = (UserVO) o;
        return enabled == userVO.enabled && Objects.equals(user_id, userVO.user_id) && Objects.equals(password, userVO.password) && Objects.equals(nickname, userVO.nickname) && Objects.equals(reg_date, userVO.reg_date) && Objects.equals(update_date, userVO.update_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, password, nickname, enabled, reg_date, update_date);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId='" + user_id + '\'' +
                ", password=[PROTECTED]" +
                ", userName='" + nickname + '\'' +
                ", enabled=" + enabled +
                ", regDate=" + reg_date +
                ", updateDate=" + update_date +
                '}';
    }

}
