package com.perfect.community.api.controller.security;

import com.perfect.community.api.controller.ControllerIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional(readOnly = true)
@DisplayName("[Login with JSON]")
public class JsonLoginTest extends ControllerIntegrationTest {

    @Test
    @DisplayName("[Login with JSON] - By not authenticated user")
    @WithAnonymousUser
    void login() throws Exception {
        Map<String, String> usernamePassword = new HashMap<>();
        usernamePassword.put("username", "admin");
        usernamePassword.put("password", "admin");
        String jsonData = objectMapper.writeValueAsString(usernamePassword);
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    @DisplayName("[Login with JSON] - By already authenticated user")
    @WithUserDetails("admin")
    void loginByAlreadyAuthenticated() throws Exception {
        Map<String, String> usernamePassword = new HashMap<>();
        usernamePassword.put("username", "admin");
        usernamePassword.put("password", "admin");
        String jsonData = objectMapper.writeValueAsString(usernamePassword);
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

}
