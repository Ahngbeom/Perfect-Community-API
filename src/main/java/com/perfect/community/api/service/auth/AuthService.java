package com.perfect.community.api.service.auth;


import com.perfect.community.api.dto.authorities.AuthoritiesDTO;

import java.util.List;

public interface AuthService {

    List<AuthoritiesDTO> getAuthorities();

    void addAuthority(AuthoritiesDTO auth);

    void removeAuthority(AuthoritiesDTO auth);

    void modifyAuthority(String userId, String role);

}
