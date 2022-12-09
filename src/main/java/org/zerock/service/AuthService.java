package org.zerock.service;

import org.zerock.DTO.AuthorityDTO;

import java.util.List;

public interface AuthService {

    List<String> getAuthList(String userId);

    void grantAuthority(AuthorityDTO auth);

    void revokeOneAuthority(AuthorityDTO auth);

    void revokeAllAuthority(String userId);

    boolean hasAdminRole(String userId);

}
