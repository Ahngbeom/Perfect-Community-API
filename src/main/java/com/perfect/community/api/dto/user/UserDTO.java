package com.perfect.community.api.dto.user;

import com.perfect.community.api.dto.auth.AuthorityDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO {

    private String userId;
    private String password;
    private String userName;
    private boolean enabled;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private List<AuthorityDTO> authList;

    @Builder
    public UserDTO(String userId, String password, String userName, boolean enabled, LocalDateTime regDate, LocalDateTime updateDate, List<AuthorityDTO> authList) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.enabled = enabled;
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.authList = authList;
    }
}
