package org.zerock.service;

import org.zerock.domain.AuthVO;
import org.zerock.domain.MemberVO;

import java.util.List;

public interface MemberService {
    List<MemberVO> getUserList();

    List<AuthVO> getAuthList(String userId);

    boolean createUser(MemberVO member, AuthVO auth);

    boolean authorizationToUser(AuthVO auth);

    boolean revokeOneAuthorityToUser(AuthVO auth);

    boolean revokeAllAuthorityToUser(String userId);

    boolean deleteUser(String userId);



}
