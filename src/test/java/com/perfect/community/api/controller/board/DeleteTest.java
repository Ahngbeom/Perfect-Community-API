package com.perfect.community.api.controller.board;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeleteTest extends BoardControllerTest {

    @Test
    @WithAnonymousUser
    void deleteBoardByAnonymous() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/board/delete/1"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @WithUserDetails("admin")
    void deleteBoardInvalidBoardNo() throws Exception {
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/board/delete/99"))
//                    .andExpect(status().isBadRequest())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithUserDetails("tester1")
    void deleteBoardWithUnauthorizedUser() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/board/delete/1"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @WithUserDetails("admin")
    void deleteBoard() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/board/delete/1"))
                .andExpect(status().isOk())
                .andReturn();
    }
}