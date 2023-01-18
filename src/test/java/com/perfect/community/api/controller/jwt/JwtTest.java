package com.perfect.community.api.controller.jwt;

import com.perfect.community.api.controller.ControllerIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[JWT TEST]")
public class JwtTest extends ControllerIntegrationTest {

//    void registration(String accessToken) throws Exception {
//        PostDTO postDTO = PostDTO.builder()
//                .boardNo(1)
//                .type(PostType.NORMAL.name())
//                .title("JUNIT INTEGRATION TEST FOR POST REGISTRATION")
//                .contents("JUST FOR POST REGISTRATION")
//                .build();
//        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
//                        .header("Authorization", accessToken)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.valueToTree(postDTO).toString()))
//                .andExpect(status().isOk())
//                .andReturn();
//    }

    @Test
    @DisplayName("[JWT TEST] - JSON Authentication")
    @WithAnonymousUser
    void Login() throws Exception {
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
    @DisplayName("[JWT TEST] - Get JWT token")
    @WithUserDetails("admin")
    void GetToken() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/jwt"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
