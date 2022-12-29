package com.perfect.community.api.controller.user;

import com.perfect.community.api.dto.user.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.security.auth.login.CredentialException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WithdrawTest extends UserControllerTest {

    String requestBody;

    @Test
    @DisplayName("[Withdraw User] - Not logged in")
    void notLogin() throws Exception {
        try {
            if (!verifyPassword("1234"))
                throw new CredentialException("Passwords do not match.");
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/withdraw/tester1"))
                    .andExpect(status().isBadRequest())
                    .andReturn();
            log.error(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("[Withdraw User] - Password do not match")
    @WithUserDetails("tester1")
    void incorrectPassword() {
        try {
            if (!verifyPassword("1234567890"))
                throw new CredentialException("Passwords do not match.");
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/withdraw/tester1"))
                    .andExpect(status().isOk())
                    .andReturn();
            String deletedUserId = mvcResult.getResponse().getContentAsString();
            if (getUserInfo(deletedUserId) == null)
                log.info("[" + deletedUserId + "] is doesn't exist.");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("[Withdraw User] - Correct Request")
    @WithUserDetails("tester1")
    void correctPassword() {
        try {
            if (!verifyPassword("1234"))
                throw new CredentialException("Passwords do not match.");
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/withdraw/tester1"))
                    .andExpect(status().isOk())
                    .andReturn();
            String deletedUserId = mvcResult.getResponse().getContentAsString();
            if (getUserInfo(deletedUserId) == null)
                log.info("[" + deletedUserId + "] is doesn't exist.");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}
