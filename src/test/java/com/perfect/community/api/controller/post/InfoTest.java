package com.perfect.community.api.controller.post;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InfoTest extends PostControllerTest {
    @Test
    void info() throws Exception {
        log.info(getPostInfo(1));
    }

    @Test
    void byInvalidPno() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/post/info/-1"))
                .andExpect(status().isBadRequest())
                .andReturn();
        log.error(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void notPathVariableForPostNo() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/post/info"))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
