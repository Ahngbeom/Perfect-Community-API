package com.perfect.community.api.controller.jwt;

import com.perfect.community.api.controller.ControllerIntegrationTest;
import com.perfect.community.api.controller.post.RegistrationTest;
import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostType;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class JwtTest extends ControllerIntegrationTest {

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

    void registration() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(1)
                .type(PostType.NORMAL.name())
                .title("JUNIT INTEGRATION TEST FOR POST REGISTRATION")
                .contents("JUST FOR POST REGISTRATION")
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.valueToTree(postDTO).toString()))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithAnonymousUser
    void RegisterPostAfterLogin() throws Exception {
        login();
        registration();
    }
}
