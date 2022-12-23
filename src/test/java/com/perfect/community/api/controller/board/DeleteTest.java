package com.perfect.community.api.controller.board;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeleteTest extends BoardControllerTest {

    @Test
    @WithUserDetails("admin")
    void deleteBoard() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/board/delete/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithUserDetails("admin")
    void deleteBoardInvalidBoardNo() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/board/delete/99"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
        log.error(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithUserDetails("tester1")
    void deleteBoardWithUnauthorizedUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/board/delete/6"))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andReturn();
        log.error(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void deleteBoardWithAnonymous() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/board/delete/1"))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andReturn();
        log.error(mvcResult.getResponse().getContentAsString());
    }
}