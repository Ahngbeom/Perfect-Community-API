package com.perfect.community.api.service.user;

import com.perfect.community.api.dto.user.UserAuthoritiesDTO;
import com.perfect.community.api.mapper.user.UsersAuthoritiesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAuthoritiesService {

    private final UsersAuthoritiesMapper mapper;

    private final UserService userService;

    public List<UserAuthoritiesDTO> getAllUserAuthorities() {
//        List<UserAuthorityEntity> userAuthorityEntities = mapper.selectAllUserAuthorities();
//        return userAuthorityEntities.stream().map(userAuthorityEntity ->
//                UserAuthoritiesDTO.Response.builder()
//                        .userId(userService.getUserInfoById(userAuthorityEntity.getUser()))
//                        .authorities(userAuthorityEntity.getAuthorities().stream().map(AuthoritiesDTO::new).collect(Collectors.toList()))
//                        .build()
//        ).collect(Collectors.toList());
        return null;
    }

    public List<UserAuthoritiesDTO> getAllUserAuthoritiesByUserId(String userId) {
        return null;
    }

    public void grantUserAuthorities(UserAuthoritiesDTO userAuthReq) {

    }

    public void revokeUserAuthorities(UserAuthoritiesDTO userAuthReq) {

    }

    public void revokeAllUserAuthoritiesByUserId(String userId) {

    }
}
