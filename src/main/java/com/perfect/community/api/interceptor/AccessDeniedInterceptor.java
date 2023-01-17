package com.perfect.community.api.interceptor;

import com.perfect.community.api.service.board.BoardService;
import com.perfect.community.api.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class AccessDeniedInterceptor implements HandlerInterceptor {

    private final BoardService boardService;

    private final PostService postService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        final String requestURI = request.getRequestURI();
        final String requestMethod = request.getMethod();
        @SuppressWarnings("unchecked")
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        if (requestURI.startsWith("/api/user")) {
            if (pathVariables.containsKey("userId")) {
                if (!pathVariables.get("userId").equals(request.getUserPrincipal().getName())
                        && !request.isUserInRole("ROLE_ADMIN")) {
                    throw new AccessDeniedException("Access denied.");
                }
            }
        } else if (requestURI.startsWith("/api/board")) {
            if (HttpMethod.GET.matches(requestMethod))
                return true;
            if (HttpMethod.POST.matches(requestMethod)) {
                if (pathVariables.containsKey("boardNo")) {
                    if (!boardService.isHeTheOwnerOfBoard(request.getUserPrincipal().getName(), Long.parseLong(pathVariables.get("boardNo")))
                            && !request.isUserInRole("ROLE_ADMIN")) {
                        throw new AccessDeniedException("Access denied.");
                    }
                }
            }

        } else if (requestURI.startsWith("/api/post")) {
            if (pathVariables.containsKey("postNo")) {
                if (!postService.isWriter(Long.parseLong(pathVariables.get("postNo")), request.getUserPrincipal().getName())
                        && !request.isUserInRole("ROLE_ADMIN")) {
                    throw new AccessDeniedException("Access denied.");
                }
            }
        }
        log.info(this.getClass().getSimpleName() + " [PASSED]");
        return true;
    }

}
