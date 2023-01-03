package com.perfect.community.api.controller.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class InfoTest extends UserControllerTest {
    @Test
    @DisplayName("[User Info] Valid User")
    void validUserInfo() throws Exception {
        log.warn(getUserInfo("admin"));
    }

    @Test
    @DisplayName("[User Info] Invalid User")
    void invalidUserInfo() throws Exception {
        log.warn(getUserInfo("a"));
    }

    @Test
    @DisplayName("[User Info] UserDetails")
    void userDetails() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/authentication"))
                        .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }
}
