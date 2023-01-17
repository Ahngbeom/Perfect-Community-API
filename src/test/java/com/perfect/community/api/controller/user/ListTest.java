package com.perfect.community.api.controller.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[User's List]")
public class ListTest extends UserControllerTest {
    @Test
    @DisplayName("[User's List] - User's List")
    void list() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/list"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
