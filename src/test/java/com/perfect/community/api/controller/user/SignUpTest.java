package com.perfect.community.api.controller.user;

import com.perfect.community.api.dto.user.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName(value = "[Integrated Controller] Sign-up")
@WithAnonymousUser
public class SignUpTest extends UserControllerTest {

    String requestBody;

    @Test
    @DisplayName(value = "General user")
    public void signUpAdmin() throws Exception {
        requestBody = objectMapper.valueToTree(
                UserDTO.builder()
                        .userId("abcde")
                        .password("abcde")
                        .nickname("ABCDE")
                        .authority("ROLE_USER")
                        .build()
        ).toString();
        log.info(requestBody);
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();
        String createdUserId = mvcResult.getResponse().getContentAsString();
        log.info(getUserInfo(createdUserId));
    }

    @Test
    @DisplayName(value = "By already authenticated user")
    @WithUserDetails("admin")
    public void signUpAdminByAuthenticated() throws Exception {
        requestBody = objectMapper.valueToTree(
                UserDTO.builder()
                        .userId("abcde")
                        .password("abcde")
                        .nickname("ABCDE")
                        .authority("ROLE_USER")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnauthorized())
                .andReturn();
        String createdUserId = mvcResult.getResponse().getContentAsString();
        log.info(getUserInfo(createdUserId));
    }

    @Test
    @DisplayName(value = "Duplicate ID")
    public void duplicateUserId() throws Exception {
        requestBody = objectMapper.valueToTree(
                UserDTO.builder()
                        .userId("admin")
                        .password("1234")
                        .nickname("ADMIN")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName(value = "Duplicate Nickname")
    public void duplicateNickname() throws Exception {
        requestBody = objectMapper.valueToTree(
                UserDTO.builder()
                        .userId("aaaaa")
                        .password("1234")
                        .nickname("Administrator")
                        .authority("ROLE_USER")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName(value = "Without authorities")
    public void withoutAuthorities() throws Exception {
        requestBody = objectMapper.valueToTree(
                UserDTO.builder()
                        .userId("ahngbeom")
                        .password("1234")
                        .nickname("AhngBeom")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andReturn();
    }


}
