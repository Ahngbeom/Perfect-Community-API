package com.perfect.community.api.security.interceptor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.perfect.community.api.dto.user.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class UserDeniedAccessInterceptor implements HandlerInterceptor {

    private static final Logger log = LogManager.getLogger(UserDeniedAccessInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        @SuppressWarnings("unchecked")
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if (pathVariables.containsKey("userId")) {
            String requestURI = request.getRequestURI();
            String targetUserId = requestURI.substring(requestURI.lastIndexOf('/') + 1);
            if (!targetUserId.equals(request.getUserPrincipal().getName()) && !request.isUserInRole("ROLE_ADMIN")) {
                throw new AccessDeniedException("User access denied.");
            }
        }
        return true;
    }

    // Get @RequestBody JSON data for verification
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        InputStream inputStream = request.getInputStream();
//        if (inputStream != null) {
//            try {
//                StringBuilder stringBuilder = new StringBuilder();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                String line;
//                while ((line = bufferedReader.readLine()) != null) {
//                    stringBuilder.append(line);
//                }
//                ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
//                UserDTO requestBody = objectMapper.readValue(stringBuilder.toString(), new TypeReference<UserDTO>() {
//                });
//                log.warn("@RequestBody: " + requestBody);
////            if (!requestBody.getUserId().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
////                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User access denied.");
////                return false;
////            }
//                if (!requestBody.getUserId().equals(request.getUserPrincipal().getName()) && !request.isUserInRole("ROLE_ADMIN")) {
//                    throw new AccessDeniedException("User access denied.");
//                }
////            log.warn(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("ROLE_ADMIN"));
////            if (!SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("ROLE_ADMIN")) {
////                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User access denied.");
////                return false;
////            }
//            } catch (Exception e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//        return true;
//    }

}
