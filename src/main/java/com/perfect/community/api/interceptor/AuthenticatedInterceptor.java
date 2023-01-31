package com.perfect.community.api.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfect.community.api.security.jwt.JwtAuthenticationFilter;
import com.perfect.community.api.security.jwt.JwtTokenProvider;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
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
		Authentication AUTHENTICATION;
		boolean IS_ADMIN;

		public REQUEST_DATA(HttpServletRequest request, Map<String, String> pathVariables, Authentication authentication) {
			this.URI = request.getRequestURI();
			this.METHOD = request.getMethod();
			this.PATH_VARIABLES = pathVariables;
			this.AUTHENTICATION = authentication;
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
		REQUEST_DATA requestData = new REQUEST_DATA(request, pathVariables, authentication);
		log.info("{}\n {}", authentication, requestData);

		if (requestData.URI.startsWith("/api/user"))
			accessVerifyUserAPI(requestData);
		else if (requestData.URI.startsWith("/api/post"))
			accessVerifyPostAPI(requestData);
		else if (requestData.URI.startsWith("/api/board"))
			accessVerifyBoardAPI(requestData);
		log.info("[PASSED] {}", this.getClass().getSimpleName());
		return true;
	}

	private void accessVerifyUserAPI(REQUEST_DATA requestData) {
		if (requestData.AUTHENTICATION instanceof AnonymousAuthenticationToken) {
			if (HttpMethod.GET.matches(requestData.METHOD)) {
				if (!requestData.URI.equals("/api/user") && !requestData.URI.endsWith("/post/recently-viewed"))
					return;
			} else if (HttpMethod.POST.matches(requestData.METHOD)) {
				if (!requestData.URI.startsWith("/api/post/scrap"))
					return;
			}
			throw new AccessDeniedException("User is not authenticated");
		} else if (requestData.AUTHENTICATION instanceof UsernamePasswordAuthenticationToken) {
			if (HttpMethod.GET.matches(requestData.METHOD)) {
				if (requestData.URI.startsWith("/api/user"))
					return;
			} else if (HttpMethod.POST.matches(requestData.METHOD)) {
				if (requestData.URI.startsWith("/api/post/scrap"))
					return;
			} else if (HttpMethod.PUT.matches(requestData.METHOD)) {
				if (requestData.PATH_VARIABLES.containsKey("userId") && requestData.PATH_VARIABLES.get("userId").equals(requestData.AUTHENTICATION.getName()))
					return;
			} else if (HttpMethod.PATCH.matches(requestData.METHOD)) {
				if (requestData.URI.startsWith("/api/user/disable") || requestData.URI.startsWith("/api/user/enable")) {
					if (requestData.IS_ADMIN || (requestData.PATH_VARIABLES.containsKey("userId") && requestData.PATH_VARIABLES.get("userId").equals(requestData.AUTHENTICATION.getName())))
						return;
				}
			} else if (HttpMethod.DELETE.matches(requestData.METHOD)) {
				if (requestData.URI.startsWith("/api/post/release-scrap"))
					return;
				else if (requestData.PATH_VARIABLES.containsKey("userId") && requestData.PATH_VARIABLES.get("userId").equals(requestData.AUTHENTICATION.getName()))
					return;
			}
			throw new AuthenticationServiceException("Access denied");
		}
	}

	private void accessVerifyBoardAPI(REQUEST_DATA requestData) {
		if (requestData.AUTHENTICATION instanceof AnonymousAuthenticationToken) {
			if (!HttpMethod.GET.matches(requestData.METHOD))
				throw new AccessDeniedException("User is not authenticated");
		} else if (requestData.AUTHENTICATION instanceof UsernamePasswordAuthenticationToken) {
			if (!HttpMethod.GET.matches(requestData.METHOD)) {
				if (HttpMethod.POST.matches(requestData.METHOD)) {
					if (!requestData.IS_ADMIN)
						throw new AuthenticationServiceException("Access denied");
				} else if (requestData.PATH_VARIABLES.containsKey("boardNo") && !boardService.isHeTheOwnerOfBoard(requestData.AUTHENTICATION.getName(), Long.parseLong(requestData.PATH_VARIABLES.get("boardNo"))))
					throw new AuthenticationServiceException("Access denied");
			}
		}
	}

	private void accessVerifyPostAPI(REQUEST_DATA requestData) {
		if (requestData.AUTHENTICATION instanceof AnonymousAuthenticationToken) {
			if (HttpMethod.GET.matches(requestData.METHOD)) {
				if (!requestData.URI.equals("/api/post/scraped"))
					return;
			} else if (HttpMethod.POST.matches(requestData.METHOD)) {
				throw new AccessDeniedException("User is not authenticated");
			} else if (HttpMethod.PATCH.matches(requestData.METHOD)) {
				// 비로그인 유저의 특정 기간 내의 최대 조회 제한 필요
//				if (requestData.URI.startsWith("/api/post/views") && requestData.PATH_VARIABLES.containsKey("postNo"))
//					return;
			}
			throw new AccessDeniedException("User is not authenticated");
		} else if (requestData.AUTHENTICATION instanceof UsernamePasswordAuthenticationToken) {
			if (HttpMethod.GET.matches(requestData.METHOD)) {
				return;
			} else if (HttpMethod.POST.matches(requestData.METHOD)) {
				if (requestData.PATH_VARIABLES.containsKey("postNo") &&
						!postService.isWriter(
								Long.parseLong(requestData.PATH_VARIABLES.get("postNo")),
								requestData.AUTHENTICATION.getName()))
					throw new AuthenticationServiceException("Access denied");
			} else if (HttpMethod.PUT.matches(requestData.METHOD)) {
				return;
			} else if (HttpMethod.PATCH.matches(requestData.METHOD)) {
				if (requestData.URI.matches("(/api/post/)(views|recommend|(not-recommend))(/\\d+)")) {
					return;
				}
				if (requestData.PATH_VARIABLES.containsKey("postNo") &&
						!postService.isWriter(
								Long.parseLong(requestData.PATH_VARIABLES.get("postNo")),
								requestData.AUTHENTICATION.getName()))
					throw new AuthenticationServiceException("Access denied");

			} else if (HttpMethod.DELETE.matches(requestData.METHOD)) {
				if (requestData.PATH_VARIABLES.containsKey("postNo") &&
						!postService.isWriter(
								Long.parseLong(requestData.PATH_VARIABLES.get("postNo")),
								requestData.AUTHENTICATION.getName()))
					throw new AuthenticationServiceException("Access denied");
			}
			throw new AuthenticationServiceException("Access denied");
		}
	}
}
