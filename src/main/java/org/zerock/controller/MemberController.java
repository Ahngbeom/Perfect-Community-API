package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.AuthVO;
import org.zerock.domain.MemberVO;
import org.zerock.security.CustomUserDetails;
import org.zerock.security.CustomUserDetailService;
import org.zerock.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/member/**")
@RequiredArgsConstructor
public class MemberController {

    private static final Logger log = LogManager.getLogger();

    private final CustomUserDetailService userDetailsService;
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
        CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(principal.getName());
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
    public ModelAndView createMemberPage(@AuthenticationPrincipal Principal principal, HttpServletRequest request, RedirectAttributes redirectAttributes, ModelAndView mv) {
        if (principal != null &&
                !((CustomUserDetails)userDetailsService.loadUserByUsername(
                        principal.getName())).getAuthorities().stream().anyMatch(authentic -> authentic.getAuthority().equals("ROLE_ADMIN"))) {
            redirectAttributes.addFlashAttribute("type", "Logout Required");
            redirectAttributes.addFlashAttribute("state", "WARNING");
            if (request.getHeader("Referer") != null && !request.getHeader("Referer").contains("/login")) // 이전 페이지가 로그인 페이지일 경우(로그인 실패, 로그인 페이지 연속 이동 등) prevPage를 설정하지 않음
                request.getSession().setAttribute("prevPage", request.getHeader("Referer"));
            else
                request.getSession().setAttribute("prevPage", "/board/list");
            mv.setViewName("redirect:" + request.getSession().getAttribute("prevPage"));
        } else {
            mv.setViewName("/member/create");
        }
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView createMember(@AuthenticationPrincipal Principal principal, RedirectAttributes redirectAttributes, ModelAndView mv, MemberVO member, AuthVO auth) {
        log.warn(member);
        log.warn(auth);
        if (memberService.createUser(member, auth)) {
            if (principal != null &&
                    ((CustomUserDetails)userDetailsService.loadUserByUsername(principal.getName())).getAuthorities().stream().anyMatch(authentic -> authentic.getAuthority().equals("ROLE_ADMIN"))) {
                redirectAttributes.addFlashAttribute("type", "Account Create");
                redirectAttributes.addFlashAttribute("state", "WARNING");
                mv.setViewName("redirect:/member/list");
            } else {
                redirectAttributes.addFlashAttribute("type", "Account Create");
                redirectAttributes.addFlashAttribute("state", "SUCCESS");
                mv.setViewName("redirect:/login");
            }
        } else {
            redirectAttributes.addFlashAttribute("type", "Account Create");
            redirectAttributes.addFlashAttribute("state", "FAILURE");
            mv.setViewName("redirect:/member/create");
        }
        return mv;
    }
}
