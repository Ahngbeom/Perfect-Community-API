package com.board.api.service.auth;


import com.board.api.dto.auth.AuthorityDTO;

import java.util.List;

public interface AuthService {

    List<AuthorityDTO> getAuthList(String userId);

    void grantAuthority(AuthorityDTO auth);

    void revokeOneAuthority(AuthorityDTO auth);

    void revokeAllAuthority(String userId);

    boolean hasRole(String userId, String role);

}
