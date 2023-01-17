package com.perfect.community.api.controller.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[Board's Delete]")
class DeleteTest extends BoardControllerTest {

    @Test
    @DisplayName("[Board's Delete] - By anonymous")
    @WithAnonymousUser
    void deleteBoardByAnonymous() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/board/1"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @DisplayName("[Board's Delete] - By not created user")
    @WithUserDetails("tester")
    void deleteBoardWithUnauthorizedUser() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/board/1"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @DisplayName("[Board's Delete] - By admin")
    @WithUserDetails("admin")
    void deleteBoard() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/board/1"))
                .andExpect(status().isOk())
                .andReturn();
    }
}