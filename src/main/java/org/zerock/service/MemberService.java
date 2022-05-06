package org.zerock.service;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.zerock.domain.MemberVO;

import java.security.Principal;
import java.util.List;

public interface MemberService {
    List<MemberVO> getUserList();

    boolean adminDeleteUser(String userId);
}
