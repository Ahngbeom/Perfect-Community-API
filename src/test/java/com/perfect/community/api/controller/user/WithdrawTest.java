package com.perfect.community.api.controller.user;

import com.perfect.community.api.dto.user.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WithdrawTest extends UserControllerTest {

    String requestBody;

    @Test
    @DisplayName("[Withdraw User] - Not logged in")
    void notLogin() {
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
    }

    @Test
    @DisplayName("[Withdraw User] - Incorrect Password")
    @WithUserDetails("tester1")
    void incorrectPassword() {
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
    }

    @Test
    @DisplayName("[Withdraw User] - Correct Request")
    @WithUserDetails("tester1")
    void correctPassword() {
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
            if (getUserInfo(deletedUserId) == null)
                log.info("[" + deletedUserId + "] is doesn't exist.");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
