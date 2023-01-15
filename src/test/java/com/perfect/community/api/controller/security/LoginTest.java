package com.perfect.community.api.controller.security;

import com.perfect.community.api.controller.ControllerIntegrationTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({
        "file:web/WEB-INF/interceptor-servlet.xml",
        "file:web/WEB-INF/securityContext.xml",
        "file:web/WEB-INF/dispatcher-servlet.xml"
})
@Rollback(value = false)
public class LoginTest extends ControllerIntegrationTest {

    @Test
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
