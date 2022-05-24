package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
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

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private static final Logger log = LogManager.getLogger();

    private final CustomUserDetailService userDetailsService;
    private final MemberService memberService;

    public final boolean isAdmin(Principal principal) {
        if (principal == null)
            return false;
        UserDetails user = userDetailsService.loadUserByUsername(principal.getName());
        log.warn("UserDetails Auth: " + user.getAuthorities());
        if (user != null && user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")))
            return true;
        else
            return false;
    }

    @GetMapping("/info")
    public ModelAndView info(RedirectAttributes redirectAttributes, ModelAndView mv, @AuthenticationPrincipal Principal principal) {
        if (principal == null) {
            redirectAttributes.addFlashAttribute("memberAlertType", "Account Access");
            redirectAttributes.addFlashAttribute("memberAlertStatus", "FAILURE");
            mv.setViewName("redirect:/login");
        } else {
            mv.setViewName("/member/info");
        }
        return mv;
    }

    @GetMapping("/list")
    public ModelAndView list(RedirectAttributes redirectAttributes, ModelAndView mv, @AuthenticationPrincipal Principal principal, Authentication authentication) {
        if (isAdmin(principal)) {
            mv.addObject("MemberList", memberService.getUserList());
            mv.setViewName("/member/list");
        } else {
            redirectAttributes.addFlashAttribute("memberAlertType", "Account Access");
            redirectAttributes.addFlashAttribute("memberAlertStatus", "FAILURE");
            mv.setViewName("redirect:/login");
        }
        return mv;
    }

    @PostMapping("/remove")
    public ModelAndView removeMember(RedirectAttributes redirectAttributes, ModelAndView mv, @AuthenticationPrincipal Principal principal, String userId) {
        CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(principal.getName());
        if (user != null) {
            if (user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                if (memberService.revokeAllAuthorityToUser(userId) && memberService.deleteUser(userId)) {
                    redirectAttributes.addFlashAttribute("memberAlertType", "Account Delete");
                    redirectAttributes.addFlashAttribute("memberAlertStatus", "SUCCESS");
//                    redirectAttributes.addFlashAttribute("userId", userId);
                    mv.setViewName("redirect:/member/list");
                }
            } else {
                log.warn("You don't have that [ADMIN] permission.");
                mv.setViewName("redirect:/");
            }
        } else {
            log.warn("Login required.");
            mv.setViewName("redirect:/login");
        }
        return mv;
    }

    @GetMapping("/create")
    public ModelAndView createMemberPage(@AuthenticationPrincipal Principal principal, HttpServletRequest request, RedirectAttributes redirectAttributes, ModelAndView mv) {
        if (isAdmin(principal)) {
            redirectAttributes.addFlashAttribute("memberAlertType", "Logout Required");
            redirectAttributes.addFlashAttribute("memberAlertStatus", "WARNING");
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
                    ((CustomUserDetails) userDetailsService.loadUserByUsername(principal.getName())).getAuthorities().stream().anyMatch(authentic -> authentic.getAuthority().equals("ROLE_ADMIN"))) {
                redirectAttributes.addFlashAttribute("memberAlertType", "Account Create");
                redirectAttributes.addFlashAttribute("memberAlertStatus", "WARNING");
                mv.setViewName("redirect:/member/list");
            } else {
                redirectAttributes.addFlashAttribute("memberAlertType", "Account Create");
                redirectAttributes.addFlashAttribute("memberAlertStatus", "SUCCESS");
                mv.setViewName("redirect:/login");
            }
        } else {
            redirectAttributes.addFlashAttribute("memberAlertType", "Account Create");
            redirectAttributes.addFlashAttribute("memberAlertStatus", "FAILURE");
            mv.setViewName("redirect:/member/create");
        }
        return mv;
    }

    @PostMapping("/disable")
    public ModelAndView disable(ModelAndView mv, RedirectAttributes redirectAttributes, Principal principal, String userId) {
        // 장기 미접속 계정 휴면 상태 전환 기능 필요
        
        if (isAdmin(principal)) {
            if (memberService.disableUser(userId)) {
                redirectAttributes.addFlashAttribute("memberAlertType", "Account Disabled");
                redirectAttributes.addFlashAttribute("memberAlertStatus", "SUCCESS");
//                    redirectAttributes.addFlashAttribute("userId", userId);
                mv.setViewName("redirect:/member/list");
            }
        } else if (principal != null) {
            log.warn("You don't have that [ADMIN] permission.");
            mv.setViewName("redirect:/");
        } else {
            log.warn("Login required.");
            mv.setViewName("redirect:/login");
        }
        return mv;
    }

    @PostMapping("/enable")
    public ModelAndView enable(ModelAndView mv, RedirectAttributes redirectAttributes, Principal principal, String userId) {
        if (isAdmin(principal)) {
            if (memberService.enableUser(userId)) {
                redirectAttributes.addFlashAttribute("memberAlertType", "Account Enabled");
                redirectAttributes.addFlashAttribute("memberAlertStatus", "SUCCESS");
//                    redirectAttributes.addFlashAttribute("userId", userId);
                mv.setViewName("redirect:/member/list");
            }
        } else if (principal != null) {
            log.warn("You don't have that [ADMIN] permission.");
            mv.setViewName("redirect:/");
        } else {
            log.warn("Login required.");
            mv.setViewName("redirect:/login");
        }
        return mv;
    }
}
