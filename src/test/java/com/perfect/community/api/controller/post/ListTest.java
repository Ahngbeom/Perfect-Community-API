package com.perfect.community.api.controller.post;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ListTest extends PostControllerTest {
    @Test
    void list() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/post/list"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
