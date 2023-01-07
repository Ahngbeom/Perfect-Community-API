package com.perfect.community.api.controller.user;

import com.perfect.community.api.dto.user.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SignUpTest extends UserControllerTest {

    String requestBody;

    @Test
    @DisplayName(value = "[Sign-Up] - Null Exception")
    public void nullException() throws Exception {
        try {
            objectMapper.valueToTree(
                    UserDTO.builder()
                            .userId(null) // must be not null.
                            .build()
            );
        } catch (NullPointerException nullPointerException) {
            log.error(nullPointerException.getMessage());
        }

        requestBody = objectMapper.valueToTree(
                UserDTO.builder()
                        .userId("test")
                        .password(null)
                        .nickname(null)
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andReturn();
        log.error(mvcResult.getResponse().getContentAsString());

        requestBody = objectMapper.valueToTree(
                UserDTO.builder()
                        .userId("test")
                        .password("testest")
                        .nickname(null)
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andReturn();
        log.error(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName(value = "[Sign-Up] - Duplicate ID")
    public void duplicateUserId() throws Exception {
        requestBody = objectMapper.valueToTree(
                UserDTO.builder()
                        .userId("admin")
                        .password("1234")
                        .nickname("ADMIN")
                        .build()
        ).toString();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andReturn();
        log.error(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName(value = "[Sign-Up] - Duplicate Nickname")
    public void duplicateNickname() throws Exception {
        requestBody = objectMapper.valueToTree(
                UserDTO.builder()
                        .userId("aaaaa")
                        .password("1234")
                        .nickname("Administrator")
                        .build()
        ).toString();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andReturn();

        log.error(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName(value = "[Sign-Up] - Without authorities")
    public void withoutAuthorities() throws Exception {
        requestBody = objectMapper.valueToTree(
                UserDTO.builder()
                        .userId("ahngbeom")
                        .password("1234")
                        .nickname("AhngBeom")
                        .build()
        ).toString();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andReturn();

        log.error(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName(value = "[Sign-Up] - Correct Request")
    public void signUpUser() {
        try {
            requestBody = objectMapper.valueToTree(
                    UserDTO.builder()
                            .userId("ahngbeom")
                            .password("1234")
                            .nickname("AhngBeom")
                            .authority("ROLE_USER")
                            .build()
            ).toString();
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/sign-up")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isOk())
                    .andReturn();
            String createdUserId = mvcResult.getResponse().getContentAsString();
            log.info(createdUserId);
            log.info(getUserInfo(createdUserId));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
