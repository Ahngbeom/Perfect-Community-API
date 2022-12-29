package com.perfect.community.api.controller.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DuplicatesCheckForNicknameTest extends UserControllerTest {
    @Test
    @DisplayName("[Nickname Availability] - Duplicate")
    void DuplicateUserNickname() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/nickname-availability")
                        .param("nickname", "Administrator"))
                .andExpect(status().isOk())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("[Nickname Availability] - Available")
    void availableUserId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/nickname-availability")
                        .param("nickname", "ABCDE"))
                .andExpect(status().isOk())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }
}
