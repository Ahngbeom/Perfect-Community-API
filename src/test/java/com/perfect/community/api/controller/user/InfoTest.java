package com.perfect.community.api.controller.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@DisplayName("[Integrated Controller] User's details")
public class InfoTest extends UserControllerTest {
    @Test
    @DisplayName("By user ID")
    void validUserInfo() throws Exception {
        log.warn(getUserInfo("admin"));
    }

    @Test
    @DisplayName("By invalid user ID")
    void invalidUserInfo() throws Exception {
        log.warn(getUserInfo("a"));
    }

    @Test
    @DisplayName("User's authenticate details")
    void userDetails() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/authentication"))
                        .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }
}
