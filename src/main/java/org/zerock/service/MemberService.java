package org.zerock.service;

import org.zerock.domain.AuthVO;
import org.zerock.domain.MemberVO;

import java.util.List;

public interface MemberService {
    List<MemberVO> getUserList();

    boolean createUser(MemberVO member, AuthVO auth);

    boolean authorizationToUser(AuthVO auth);

    boolean deleteUser(String userId);

    boolean adminSingleAuthorityDeleteUser();

}
