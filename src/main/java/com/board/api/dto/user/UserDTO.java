package com.board.api.dto.user;

import com.board.api.dto.auth.AuthorityDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString()
public class UserDTO {

    @NonNull
    private String userId;
    @NonNull
    private String password;
    @NonNull
    private String userName;

    private boolean enabled;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    private List<AuthorityDTO> authList;

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

    public void setAuthList(List<AuthorityDTO> authList) {
        this.authList = authList;
    }

//    @Override
//    public String toString() {
//        return "UserDTO{" +
//                "userId='" + userId + '\'' +
//                ", password=" + "[PROTECTED]" +
//                ", userName='" + userName + '\'' +
//                ", enabled=" + enabled +
//                ", regDate=" + regDate +
//                ", updateDate=" + updateDate +
//                ", authList=" + authList +
//                '}';
//    }
}
