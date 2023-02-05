package com.perfect.community.api.controller.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[Integrated Controller] Post's details")
public class InfoTest extends PostControllerTest {
    @Test
    @DisplayName("By authenticated user")
    void info() throws Exception {
        log.info(getPostInfo(1));
    }

    @Test
    @DisplayName("By anonymous user")
    @WithAnonymousUser
    void infoByAnonymous() throws Exception {
        log.info(getPostInfo(1));
    }

    @Test
    @DisplayName("If post no is invalid")
    void byInvalidPno() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/post/-1"))
                .andExpect(status().isBadRequest())
                .andReturn();
        log.error(mvcResult.getResponse().getContentAsString());
    }

}
