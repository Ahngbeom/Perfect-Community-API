package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.zerock.DTO.AuthorityDTO;
import org.zerock.service.MemberService;

import java.security.Principal;

@RestController
@RequestMapping("/member/auth")
@RequiredArgsConstructor
public class AuthController {
    private static final Logger log = LogManager.getLogger();

    //    private final CustomUserDetailService userDetailsService;
    private final MemberService memberService;

    @PostMapping("/update")
    public boolean updateAuthority(Principal principal, AuthorityDTO authority, boolean isAdd) {
//        log.warn(authorization.getUserId() + ", " + authorization.getAuth() + ", " + isAdd);
//        log.warn(memberService.getAuthList(authorization.getUserId()));
        for (AuthorityDTO auth : memberService.getAuthList(principal.getName())) {
            if (isAdd){
                if (auth.getAuthority().equals(authority.getAuthority())) {
                    log.error("이미 존재하는 권한입니다.");
                    return false;
                }
            }
        }
        return isAdd ? memberService.authorizationToUser(authority) : memberService.revokeOneAuthorityToUser(authority);
    }

    @PostMapping("/removeAll")
    public boolean revokeAllAuthority(String userId) {
        return memberService.revokeAllAuthorityToUser(userId);
    }

}
