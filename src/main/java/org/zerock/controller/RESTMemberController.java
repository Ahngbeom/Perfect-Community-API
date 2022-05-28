package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.MemberVO;
import org.zerock.security.detail.CustomUserDetailService;
import org.zerock.service.MemberService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class RESTMemberController {

    private static final Logger log = LogManager.getLogger();

    private final CustomUserDetailService userDetailsService;
    private final MemberService memberService;

    @GetMapping("/info/json")
    public UserDetails info(@AuthenticationPrincipal Principal principal, RedirectAttributes redirectAttributes, String userId) {
        if (principal == null) {
            redirectAttributes.addFlashAttribute("memberAlertType", "Account Access");
            redirectAttributes.addFlashAttribute("memberAlertStatus", "FAILURE");
            return null;
        } else {
            return userDetailsService.loadUserByUsername(userId);
        }
    }

    @GetMapping("/list/json")
    public List<MemberVO> list(RedirectAttributes redirectAttributes, ModelAndView mv, @AuthenticationPrincipal Principal principal) {
        UserDetails userDetails = null;
        if (principal != null) {
//            Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>(userDetailsService.loadUserByUsername(principal.getName()).getAuthorities());
//            log.warn("UserDetails Auth: " + grantedAuthorities);
            if (userDetailsService.loadUserByUsername(principal.getName()).getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")))
                return memberService.getUserList();
        }
        return null;
    }

    @PostMapping("/userId-duplicatesCheck")
    public boolean userIdDuplicatesChecking(String userId) {
        UserDetails user = userDetailsService.loadUserByUsername(userId);
        return user != null ? true : false;
    }

//    @PostMapping("/username-duplicatesCheck")
//    public boolean usernameDuplicatesChecking(String username) {
//        UserDetails user = userDetailsService.loadUserByUsername(username);
//        return user != null ? true : false;
//    }
//
//    @PostMapping("/remove")
//    public ModelAndView removeMember(RedirectAttributes redirectAttributes, ModelAndView mv, @AuthenticationPrincipal Principal principal, String userId) {
//        CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(principal.getName());
//        if (user != null) {
//            if (user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
//                if (memberService.deleteUser(userId)) {
//                    redirectAttributes.addFlashAttribute("memberAlertType", "Account Delete");
//                    redirectAttributes.addFlashAttribute("memberAlertStatus", "SUCCESS");
////                    redirectAttributes.addFlashAttribute("userId", userId);
//                }
//            } else {
//                log.warn("You don't have that [ADMIN] permission.");
//            }
//        } else {
//            log.warn("Login required.");
//        }
//        mv.setViewName("redirect:/member/list");
//        return mv;
//    }
//
//    @GetMapping("/create")
//    public ModelAndView createMemberPage(@AuthenticationPrincipal Principal principal, HttpServletRequest request, RedirectAttributes redirectAttributes, ModelAndView mv) {
//        if (principal != null &&
//                !((CustomUserDetails)userDetailsService.loadUserByUsername(
//                        principal.getName())).getAuthorities().stream().anyMatch(authentic -> authentic.getAuthority().equals("ROLE_ADMIN"))) {
//            redirectAttributes.addFlashAttribute("memberAlertType", "Logout Required");
//            redirectAttributes.addFlashAttribute("memberAlertStatus", "WARNING");
//            if (request.getHeader("Referer") != null && !request.getHeader("Referer").contains("/login")) // 이전 페이지가 로그인 페이지일 경우(로그인 실패, 로그인 페이지 연속 이동 등) prevPage를 설정하지 않음
//                request.getSession().setAttribute("prevPage", request.getHeader("Referer"));
//            else
//                request.getSession().setAttribute("prevPage", "/board/list");
//            mv.setViewName("redirect:" + request.getSession().getAttribute("prevPage"));
//        } else {
//            mv.setViewName("/member/create");
//        }
//        return mv;
//    }
//
//    @PostMapping("/create")
//    public ModelAndView createMember(@AuthenticationPrincipal Principal principal, RedirectAttributes redirectAttributes, ModelAndView mv, MemberVO member, AuthVO auth) {
//        log.warn(member);
//        log.warn(auth);
//        if (memberService.createUser(member, auth)) {
//            if (principal != null &&
//                    ((CustomUserDetails)userDetailsService.loadUserByUsername(principal.getName())).getAuthorities().stream().anyMatch(authentic -> authentic.getAuthority().equals("ROLE_ADMIN"))) {
//                redirectAttributes.addFlashAttribute("memberAlertType", "Account Create");
//                redirectAttributes.addFlashAttribute("memberAlertStatus", "WARNING");
//                mv.setViewName("redirect:/member/list");
//            } else {
//                redirectAttributes.addFlashAttribute("memberAlertType", "Account Create");
//                redirectAttributes.addFlashAttribute("memberAlertStatus", "SUCCESS");
//                mv.setViewName("redirect:/login");
//            }
//        } else {
//            redirectAttributes.addFlashAttribute("memberAlertType", "Account Create");
//            redirectAttributes.addFlashAttribute("memberAlertStatus", "FAILURE");
//            mv.setViewName("redirect:/member/create");
//        }
//        return mv;
//    }
}
