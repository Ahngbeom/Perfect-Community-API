package com.perfect.community.api.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfect.community.api.jwt.JwtAuthenticationFilter;
import com.perfect.community.api.jwt.JwtTokenProvider;
import com.perfect.community.api.security.detail.CustomUserDetailService;
import com.perfect.community.api.service.board.BoardService;
import com.perfect.community.api.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
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

    @ToString()
    static class REQUEST_DATA {
        String URI;
        String METHOD;
        Map<String, String> PATH_VARIABLES;
        String ACCESS_TOKEN;
        String USER_ID;
        boolean IS_ADMIN;

        public REQUEST_DATA(HttpServletRequest request, Map<String, String> pathVariables, String userId) {
            this.URI = request.getRequestURI();
            this.METHOD = request.getMethod();
            this.PATH_VARIABLES = pathVariables;
            this.ACCESS_TOKEN = request.getHeader(JwtAuthenticationFilter.AUTHORIZATION_HEADER);
            this.ACCESS_TOKEN = this.ACCESS_TOKEN != null ? this.ACCESS_TOKEN.substring("Bearer ".length()) : null;
            this.USER_ID = userId;
            this.IS_ADMIN = request.isUserInRole("ROLE_ADMIN");
        }
    }

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        @SuppressWarnings("unchecked")
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        REQUEST_DATA requestData = new REQUEST_DATA(request, pathVariables, authentication.getName());
        log.info("{}\n {}",  authentication, requestData);

        if (authentication instanceof AnonymousAuthenticationToken) {
            if (requestData.URI.startsWith("/api/user")) {
                permitAllByUserAPI(requestData);
            } else if (requestData.URI.startsWith("/api/board")) {
                if (!HttpMethod.GET.matches(requestData.METHOD))
                    throw new AccessDeniedException("User is not authenticated");
            } else if (requestData.URI.startsWith("/api/post")) {
                permitAllByPostAPI(requestData);
            }
        } else {
//            log.warn("Request username = {}", authentication.getName());

            if (requestData.URI.startsWith("/api/user")) {
                restrictedUserAPI(requestData);
            } else if (requestData.URI.startsWith("/api/post")) {
                restrictedPostAPI(requestData);
            }
            if (requestData.URI.startsWith("/api/board")) {
                if (HttpMethod.POST.matches(requestData.METHOD)) {
                    if (!request.isUserInRole("ROLE_ADMIN"))
                        throw new AuthenticationServiceException("Access denied");
                } else if (requestData.PATH_VARIABLES.containsKey("boardNo") && !boardService.isHeTheOwnerOfBoard(request.getUserPrincipal().getName(), Long.parseLong(requestData.PATH_VARIABLES.get("boardNo"))))
                    throw new AuthenticationServiceException("Access denied");
            } else if (requestData.PATH_VARIABLES.containsKey("boardNo") && !boardService.isHeTheOwnerOfBoard(request.getUserPrincipal().getName(), Long.parseLong(requestData.PATH_VARIABLES.get("boardNo"))))
                throw new AuthenticationServiceException("Access denied");
        }
        log.info("[PASSED] {}", this.getClass().getSimpleName());
        return true;
    }

    private void permitAllByUserAPI(REQUEST_DATA requestData) {
        if (HttpMethod.GET.matches(requestData.METHOD)) {
            if (!requestData.URI.equals("/api/user/scraped-posts"))
                return;
        } else if (HttpMethod.POST.matches(requestData.METHOD)) {
            if (!requestData.URI.startsWith("/api/user/scrap-post"))
                return;
        }
        throw new AccessDeniedException("User is not authenticated");
    }

    private void restrictedUserAPI(REQUEST_DATA requestData) {
        if (HttpMethod.GET.matches(requestData.METHOD)) {
            if (requestData.URI.startsWith("/api/user"))
                return;
        } else if (HttpMethod.POST.matches(requestData.METHOD)) {
            if (requestData.URI.startsWith("/api/user/scrap-post"))
                return;
        } else if (HttpMethod.PUT.matches(requestData.METHOD)) {
            if (requestData.PATH_VARIABLES.containsKey("userId") && requestData.PATH_VARIABLES.get("userId").equals(requestData.USER_ID))
                return;
        } else if (HttpMethod.PATCH.matches(requestData.METHOD)) {
            if (requestData.URI.startsWith("/api/user/disable") || requestData.URI.startsWith("/api/user/enable")) {
                if (requestData.IS_ADMIN || (requestData.PATH_VARIABLES.containsKey("userId") && requestData.PATH_VARIABLES.get("userId").equals(requestData.USER_ID)))
                    return;
            }
        } else if (HttpMethod.DELETE.matches(requestData.METHOD)) {
            if (requestData.URI.startsWith("/api/user/release-scraped-post"))
                return;
            else if (requestData.PATH_VARIABLES.containsKey("userId") && requestData.PATH_VARIABLES.get("userId").equals(requestData.USER_ID))
                return;
        }
        throw new AuthenticationServiceException("Access denied");
    }

    private void permitAllByPostAPI(REQUEST_DATA requestData) {
        if (HttpMethod.GET.matches(requestData.METHOD)) {
            return;
        } else if (HttpMethod.POST.matches(requestData.METHOD)) {
            throw new AccessDeniedException("User is not authenticated");
        } else if (HttpMethod.PATCH.matches(requestData.METHOD)) {
            // 비로그인 유저의 특정 기간 내의 최대 조회 제한 필요
//            if (requestData.URI.startsWith("/api/post/views") && requestData.PATH_VARIABLES.containsKey("postNo"))
//                return;
        }
        throw new AccessDeniedException("User is not authenticated");
    }

    private void restrictedPostAPI(REQUEST_DATA requestData) {
        if (HttpMethod.GET.matches(requestData.METHOD)) {
            return;
        } else if (HttpMethod.POST.matches(requestData.METHOD)) {
            if (requestData.PATH_VARIABLES.containsKey("postNo") &&
                    !postService.isWriter(
                            Long.parseLong(requestData.PATH_VARIABLES.get("postNo")),
                            requestData.USER_ID))
                throw new AuthenticationServiceException("Access denied");
            return;
        } else if (HttpMethod.PUT.matches(requestData.METHOD)) {
            return;
        } else if (HttpMethod.PATCH.matches(requestData.METHOD)) {
            if (requestData.PATH_VARIABLES.containsKey("postNo") &&
                    !postService.isWriter(
                            Long.parseLong(requestData.PATH_VARIABLES.get("postNo")),
                            requestData.USER_ID))
                throw new AuthenticationServiceException("Access denied");
            return;
        } else if (HttpMethod.DELETE.matches(requestData.METHOD)) {
            if (requestData.PATH_VARIABLES.containsKey("postNo") &&
                    !postService.isWriter(
                            Long.parseLong(requestData.PATH_VARIABLES.get("postNo")),
                            requestData.USER_ID))
                throw new AuthenticationServiceException("Access denied");
            return;
        }
        throw new AuthenticationServiceException("Access denied");
    }
}
