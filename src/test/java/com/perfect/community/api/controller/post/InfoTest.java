package com.perfect.community.api.controller.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[Post's Info]")
public class InfoTest extends PostControllerTest {
    @Test
    @DisplayName("[Post's Info] - By authenticated user")
    void info() throws Exception {
        log.info(getPostInfo(1));
    }

    @Test
    @DisplayName("[Post's Info] - By anonymous")
    @WithAnonymousUser
    void infoByAnonymous() throws Exception {
        log.info(getPostInfo(1));
    }

    @Test
    @DisplayName("[Post's Info] - Invalid post no")
    void byInvalidPno() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/post/-1"))
                .andExpect(status().isBadRequest())
                .andReturn();
        log.error(mvcResult.getResponse().getContentAsString());
    }

}
