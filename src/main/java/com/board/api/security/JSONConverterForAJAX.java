package com.board.api.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JSONConverterForAJAX {

    protected void JSONConverter(HttpServletResponse response, Authentication authentication, Map<String, Object> map) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(); // JSON 변환

        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(objectMapper.writeValueAsString(map));
        response.getWriter().flush();
//        System.out.println(map.get("redirectURL").toString());
//        response.sendRedirect(map.get("redirectURL").toString());
    }
}
