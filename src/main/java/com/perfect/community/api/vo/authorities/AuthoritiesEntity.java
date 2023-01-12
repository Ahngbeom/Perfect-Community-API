package com.perfect.community.api.vo.authorities;

import com.perfect.community.api.dto.authorities.AuthoritiesDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class AuthoritiesEntity {
    private String authority;

    @Builder
    public AuthoritiesEntity(String authority) {
        this.authority = authority;
    }

    public AuthoritiesDTO toDTO() {
        return AuthoritiesDTO.builder()
                .authority(this.authority)
                .build();
    }

    public static AuthoritiesDTO toDTO(AuthoritiesEntity entity) {
        return AuthoritiesDTO.builder()
                .authority(entity.getAuthority())
                .build();
    }

}
