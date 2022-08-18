package org.zerock.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.zerock.domain.AuthVO;
import org.zerock.domain.MemberVO;

import java.util.List;

public interface MemberService {
    List<MemberVO> getUserList();

    List<AuthVO> getAuthList(String userId);

    boolean createUser(MemberVO member, AuthVO auth);

    MemberVO readUser(String userId);

    boolean deleteUser(String userId);

    boolean disableUser(String userId);

    boolean authorizationToUser(AuthVO auth);

    boolean revokeOneAuthorityToUser(AuthVO auth);

    boolean revokeAllAuthorityToUser(String userId);

    boolean enableUser(String userId);

    boolean hasAdminRole(String userId);

    MemberVO dtoConverter(UserDetails userDetails);

}
