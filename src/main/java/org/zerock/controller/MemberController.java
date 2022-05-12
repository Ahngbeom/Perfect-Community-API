package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.AuthVO;
import org.zerock.domain.MemberVO;
import org.zerock.security.CustomUser;
import org.zerock.service.MemberService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/member/**")
@RequiredArgsConstructor
public class MemberController {

    private static final Logger log = LogManager.getLogger();

    private final UserDetailsService userDetailsService;
    private final MemberService memberService;

    @GetMapping("/info")
    public ModelAndView info(RedirectAttributes redirectAttributes, ModelAndView mv, @AuthenticationPrincipal Principal principal) {
        if (principal == null) {
            redirectAttributes.addFlashAttribute("type", "Account");
            redirectAttributes.addFlashAttribute("state", "WARNING");
            mv.setViewName("redirect:/login");
        } else {
            mv.setViewName("/member/info");
        }
        return mv;
    }

    @GetMapping("/list")
    public ModelAndView list(RedirectAttributes redirectAttributes, ModelAndView mv, @AuthenticationPrincipal Principal principal, Authentication authentication) {
        UserDetails userDetails = null;
        if (principal != null) {
            Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>(userDetailsService.loadUserByUsername(principal.getName()).getAuthorities());
            log.warn("UserDetails Auth: " + grantedAuthorities);
            if (grantedAuthorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                mv.addObject("MemberList", memberService.getUserList());
                mv.setViewName("/member/list");
            } else {
                redirectAttributes.addFlashAttribute("type", "Account");
                redirectAttributes.addFlashAttribute("state", "FAILURE");
                mv.setViewName("redirect:/board/list");
            }
        } else {
            redirectAttributes.addFlashAttribute("type", "Account");
            redirectAttributes.addFlashAttribute("state", "WARNING");
            mv.setViewName("redirect:/login");
        }
        return mv;
    }

    @PostMapping("/remove")
    public ModelAndView removeMember(RedirectAttributes redirectAttributes, ModelAndView mv, @AuthenticationPrincipal Principal principal, String userId) {
        CustomUser user = (CustomUser) userDetailsService.loadUserByUsername(principal.getName());
        if (user != null) {
            if (user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                if (memberService.deleteUser(userId)) {
                    redirectAttributes.addFlashAttribute("type", "Account Delete");
                    redirectAttributes.addFlashAttribute("state", "SUCCESS");
                    redirectAttributes.addFlashAttribute("userId", userId);
                }
            } else {
                log.warn("You don't have that [ADMIN] permission.");
            }
        } else {
            log.warn("Login required.");
        }
        mv.setViewName("redirect:/member/list");
        return mv;
    }

    @GetMapping("/create")
    public ModelAndView createMemberPage(RedirectAttributes redirectAttributes, ModelAndView mv, MemberVO member) {
        mv.setViewName("/member/create");
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView createMember(RedirectAttributes redirectAttributes, ModelAndView mv, MemberVO member, AuthVO auth) {
        log.warn(member);
        log.warn(auth);
        if (memberService.createUser(member, auth)) {
            redirectAttributes.addFlashAttribute("type", "Create User");
            redirectAttributes.addFlashAttribute("state", "SUCCESS");
            mv.setViewName("redirect:/");
        } else {
            redirectAttributes.addFlashAttribute("type", "Create User");
            redirectAttributes.addFlashAttribute("state", "FAILURE");
            mv.setViewName("redirect:/member/create");
        }
        return mv;
    }
}
