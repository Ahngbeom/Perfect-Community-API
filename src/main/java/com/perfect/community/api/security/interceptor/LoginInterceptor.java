package com.perfect.community.api.security.interceptor;

import com.perfect.community.api.security.CustomAuthenticationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger log = LogManager.getLogger(LoginInterceptor.class);

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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, AccountNotFoundException {
//        log.warn(request.getRequestURI());
//        log.warn(request.getUserPrincipal());
//        log.warn(SecurityContextHolder.getContext().getAuthentication());

        @SuppressWarnings("unchecked")
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            if (request.getRequestURI().startsWith("/api/user")) {
                if (HttpMethod.POST.matches(request.getMethod()))
                    return true;
                else if (HttpMethod.GET.matches(request.getMethod()) && !pathVariables.containsKey("userId"))
                    return true;
            } else if (request.getRequestURI().startsWith("/api/board")) {
                if (HttpMethod.GET.matches(request.getMethod()))
                    return true;
            } else if (request.getRequestURI().startsWith("/api/post")) {
                if (HttpMethod.GET.matches(request.getMethod()))
                    return true;
            }
            throw new AccessDeniedException("Not logged in.");
        }
        else if (request.getRequestURI().equals("/login")) {
            throw new AccessDeniedException("You are already logged in. Please try again after logging out.");
        }
        return true;
    }

}
