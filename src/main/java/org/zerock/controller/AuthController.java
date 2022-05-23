package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.zerock.domain.AuthVO;
import org.zerock.service.MemberService;

@RestController
@RequestMapping("/member/auth")
@RequiredArgsConstructor
public class AuthController {
    private static final Logger log = LogManager.getLogger();

    //    private final CustomUserDetailService userDetailsService;
    private final MemberService memberService;

    @PostMapping("/update")
    public boolean updateAuthority(AuthVO authorization, boolean isAdd) {
//        log.warn(authorization.getUserId() + ", " + authorization.getAuth() + ", " + isAdd);
//        log.warn(memberService.getAuthList(authorization.getUserId()));
        for (AuthVO auth : memberService.getAuthList(authorization.getUserId())) {
            if (isAdd){
                if (auth.getAuth().equals(authorization.getAuth())) {
                    log.error("이미 존재하는 권한입니다.");
                    return false;
                }
            }
        }
        return isAdd ? memberService.authorizationToUser(authorization) : memberService.revokeOneAuthorityToUser(authorization);
    }

    @PostMapping("/removeAll")
    public boolean revokeAllAuthority(String userId) {
        return memberService.revokeAllAuthorityToUser(userId);
    }
}
