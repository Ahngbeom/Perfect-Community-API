package com.perfect.community.api.service.user;

import com.perfect.community.api.dto.user.UserAuthoritiesDTO;

import java.util.List;

public interface UserAuthoritiesService {

    List<UserAuthoritiesDTO> getAllUserAuthorities();
    List<UserAuthoritiesDTO> getAllUserAuthoritiesByUserId(String userId);
    void grantUserAuthorities(UserAuthoritiesDTO userAuthReq);
    void revokeUserAuthorities(UserAuthoritiesDTO userAuthReq);
    void revokeAllUserAuthoritiesByUserId(String userId);
}
