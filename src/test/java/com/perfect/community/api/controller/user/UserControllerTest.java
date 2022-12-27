package com.perfect.community.api.controller.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.perfect.community.api.controller.ControllerTest;
import com.perfect.community.api.dto.board.BoardDTO;
import com.perfect.community.api.dto.user.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends ControllerTest {

    @Autowired
    private UserController controller;

    @BeforeEach
    void setUp() {
        setUp(controller);
    }

    UserDTO getUserInfo(String userId) throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/info")
                        .param("userId", userId))
//                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        if (mvcResult.getResponse().getContentAsString().isEmpty())
            return null;
        return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserDTO.class);
    }

    @Test
    void info() throws Exception {
        log.warn(getUserInfo("admin"));
    }

    @Test
    void list() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/list"))
//                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        log.warn(objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<UserDTO>>(){}));
    }

    @Test
    void signUpUser() {
        String requestBody;

        // Fields that must not be null in the UserDTO
        try {
            requestBody = objectMapper.valueToTree(
                    UserDTO.builder()
                            .userId(null) // must be not null.
                            .password(null) // must be not null.
                            .nickname(null) // must be not null.
                            .build()
            ).toString();

        } catch (NullPointerException nullPointerException) {
            log.error(nullPointerException.getMessage());
        }

        // Duplicate ID or Nickname
        try {
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
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        // Without authorities.
        try {
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
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        // Correct request
        try {
            requestBody = objectMapper.valueToTree(
                    UserDTO.builder()
                            .userId("ahngbeom")
                            .password("1234")
                            .nickname("AhngBeom")
                            .authorities(Collections.singletonList("ROLE_USER"))
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

    @Test
    @WithUserDetails("tester1")
    void updateUserByOtherUser() {
        String requestBody;
        try {
            requestBody = objectMapper.valueToTree(
                    UserDTO.builder()
                            .userId("admin")
                            .nickname("A")
                            .build()
            ).toString();
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/user/update")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isUnauthorized())
                    .andReturn();
            log.error(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @WithUserDetails("admin")
    void updateUser() {
        String requestBody;
        try {
            requestBody = objectMapper.valueToTree(
                    UserDTO.builder()
                            .userId("admin")
                            .nickname("A")
                            .build()
            ).toString();
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/user/update")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isOk())
                    .andReturn();
            String updatedUserId = mvcResult.getResponse().getContentAsString();
            log.info(updatedUserId);
            log.info(getUserInfo(updatedUserId));

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void withdrawUser() {
        String requestBody;
        try {
            requestBody = objectMapper.valueToTree(
                    UserDTO.builder()
                            .userId("tester1")
                            .password("incorrectPassword")
                            .build()
            ).toString();
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/withdraw")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isBadRequest())
                    .andReturn();
            log.error(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        try {
            requestBody = objectMapper.valueToTree(
                    UserDTO.builder()
                            .userId("tester1")
                            .password("1234")
                            .build()
            ).toString();
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/withdraw")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isOk())
                    .andReturn();

            String deletedUserId = mvcResult.getResponse().getContentAsString();
            log.info(deletedUserId);
            log.info(getUserInfo(deletedUserId));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void disable() {
        String requestBody;
        try {
            requestBody = objectMapper.valueToTree(
                    UserDTO.builder()
                            .userId("admin")
                            .password("incorrectPassword")
                            .build()
            ).toString();
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/user/disable")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isBadRequest())
                    .andReturn();
            log.error(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void enable() {
    }

    @Test
    void verifyPassword() {
    }

    @Test
    void userIdDuplicatesChecking() {
    }

    @Test
    void usernameDuplicatesChecking() {
    }
}