package org.zerock.DTO;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthorityDTO {

    private String userId;
    private String authority;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
