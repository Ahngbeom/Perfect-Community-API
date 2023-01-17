package com.perfect.community.api.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfect.community.api.jwt.JwtAuthenticationFilter;
import com.perfect.community.api.jwt.JwtTokenProvider;
import com.perfect.community.api.security.detail.CustomUserDetailService;
import com.perfect.community.api.service.board.BoardService;
import com.perfect.community.api.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class AuthenticatedInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;
    private final CustomUserDetailService userDetailService;
    private final BoardService boardService;
    private final PostService postService;

//    @SuppressWarnings("unchecked")
    private Map<String, String> pathVariables;

    /**
     * Interception point before the execution of a handler. Called after
     * HandlerMapping determined an appropriate handler object, but before
     * HandlerAdapter invokes the handler.
     * <p>DispatcherServlet processes a handler in an execution chain, consisting
     * of any number of interceptors, with the handler itself at the end.
     * With this method, each interceptor can decide to abort the execution chain,
     * typically sending an HTTP error or writing a custom response.
     * <p><strong>Note:</strong> special considerations apply for asynchronous
     * request processing. For more details see
     * {@link AsyncHandlerInterceptor}.
     * <p>The default implementation returns {@code true}.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return {@code true} if the execution chain should proceed with the
     * next interceptor or the handler itself. Else, DispatcherServlet assumes
     * that this interceptor has already dealt with the response itself.
     * @throws Exception in case of errors
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        final String requestURI = request.getRequestURI();
        final String requestMethod = request.getMethod();
        final String authorizationHeaderValue = request.getHeader(JwtAuthenticationFilter.AUTHORIZATION_HEADER);
        final String accessToken = authorizationHeaderValue != null ? authorizationHeaderValue.substring("Bearer ".length()) : null;

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.warn("Request URI = {}", requestURI);
        log.warn("Request Method = {}", requestMethod);

        pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        log.warn("Path variables = {}", pathVariables);

        if (authentication instanceof AnonymousAuthenticationToken) {
            if (requestURI.startsWith("/api/user")) {
                permitAllByUserAPI(requestURI, requestMethod);
            } else if (requestURI.startsWith("/api/board")) {
                if (!HttpMethod.GET.matches(requestMethod))
                    throw new AccessDeniedException("User is not authenticated");
            } else if (requestURI.startsWith("/api/post")) {
                if (requestURI.startsWith("/api/post/views") && pathVariables.containsKey("postNo")) // 비로그인 유저의 특정 기간 내의 최대 조회 제한 필요
                    return true;
                if (!HttpMethod.GET.matches(requestMethod))
                    throw new AccessDeniedException("User is not authenticated");
            }
        } else {
            log.warn("Request username = {}", authentication.getName());

            if (requestURI.startsWith("/api/user")) {
                restrictedUserAPI(requestURI, requestMethod, authentication.getName(), request.isUserInRole("ROLE_ADMIN"));
            } else if (!HttpMethod.GET.matches(requestMethod)) {
                if (pathVariables.containsKey("postNo") && !postService.isWriter(Long.parseLong(pathVariables.get("postNo")), request.getUserPrincipal().getName()))
                    throw new AccessDeniedException("Access denied");
                if (requestURI.startsWith("/api/board")) {
                    if (HttpMethod.POST.matches(requestMethod)) {
                        if (!request.isUserInRole("ROLE_ADMIN"))
                            throw new AccessDeniedException("Access denied");
                    } else if (pathVariables.containsKey("boardNo") && !boardService.isHeTheOwnerOfBoard(request.getUserPrincipal().getName(), Long.parseLong(pathVariables.get("boardNo"))))
                        throw new AccessDeniedException("Access denied");
                } else if (pathVariables.containsKey("boardNo") && !boardService.isHeTheOwnerOfBoard(request.getUserPrincipal().getName(), Long.parseLong(pathVariables.get("boardNo"))))
                    throw new AccessDeniedException("Access denied");
            }
        }
//        else if (authorizationHeaderValue == null) {
//            throw new AccessDeniedException("Not exist JWT token");
//        } else if (!jwtTokenProvider.validateToken(accessToken)) {
//            throw new AccessDeniedException("Invalid JWT token");
//        }
        log.info(this.getClass().getSimpleName() + " [PASSED]");
        return true;
    }

    private void permitAllByUserAPI(String requestURI, String requestMethod) {
        if (HttpMethod.GET.matches(requestMethod)) {
            if (!requestURI.equals("/api/user/scraped-posts"))
                return;
        } else if (HttpMethod.POST.matches(requestMethod)) {
            if (!requestURI.startsWith("/api/user/scrap-post"))
                return;
        }
        throw new AccessDeniedException("User is not authenticated");
    }

    private void restrictedUserAPI(String requestURI, String requestMethod, String requestUser, boolean isAdmin) {
        if (HttpMethod.GET.matches(requestMethod)) {
            if (requestURI.startsWith("/api/user"))
                return;
        } else if (HttpMethod.POST.matches(requestMethod)) {
            if (requestURI.startsWith("/api/user/scrap-post"))
                return;
        } else if (HttpMethod.PUT.matches(requestMethod)) {
            if (pathVariables.containsKey("userId") && pathVariables.get("userId").equals(requestUser))
                return;
        } else if (HttpMethod.PATCH.matches(requestMethod)) {
            if (requestURI.startsWith("/api/user/disable") || requestURI.startsWith("/api/user/enable")) {
                if (isAdmin || (pathVariables.containsKey("userId") && pathVariables.get("userId").equals(requestUser)))
                    return;
            }
        } else if (HttpMethod.DELETE.matches(requestMethod)) {
            if (requestURI.startsWith("/api/user/release-scraped-post"))
                return;
            else if (pathVariables.containsKey("userId") && pathVariables.get("userId").equals(requestUser))
                return;
        }
        throw new AccessDeniedException("Access denied");
    }

}
