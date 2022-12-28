package com.perfect.community.api.controller.user;

import com.perfect.community.api.dto.user.UserDTO;
import lombok.With;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.NestedServletException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DisableTest extends UserControllerTest {

    String requestBody;

    @Test
    @DisplayName("[Disable User] - Not logged in")
    void notLogin() {
        try {
            requestBody = objectMapper.valueToTree(
                    UserDTO.builder()
                            .userId("admin")
                            .password("1234")
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
    @DisplayName("[Disable User] - By Other User")
    @WithUserDetails("tester1")
    void byOtherUser() {
        try {
            requestBody = objectMapper.valueToTree(
                    UserDTO.builder()
                            .userId("admin")
                            .password("1234")
                            .build()
            ).toString();
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/user/disable")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isUnauthorized())
                    .andReturn();
            log.error(mvcResult.getResponse().getErrorMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void enable() {
    }
}
