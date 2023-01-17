package com.perfect.community.api.controller.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[Post's Remove]")
public class RemoveTest extends PostControllerTest {
    @Test
    @DisplayName("[Post's Remove] - By written user")
    @WithUserDetails("admin")
    void remove() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/post/1"))
                .andExpect(status().isOk())
                .andReturn();
        log.info("The post requested to be deleted is now " + getPostInfo(Long.parseLong(mvcResult.getResponse().getContentAsString())));
    }

    @Test
    @DisplayName("[Post's Remove] - By anonymous")
    @WithAnonymousUser
    void byAnonymous() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/post/1"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @DisplayName("[Post's Remove] - By other user")
    @WithUserDetails("tester")
    void byOtherUser() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/post/1"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @DisplayName("[Post's Remove] - invalid post no")
    @WithUserDetails("admin")
    void byInvalidPno() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/post/-1"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }
}
