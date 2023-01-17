package com.perfect.community.api.controller.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[Disable User]")
public class DisableTest extends UserControllerTest {

    @Test
    @DisplayName("[Disable User] - Not logged in")
    @WithAnonymousUser
    void notLogin() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/user/disable/admin"))
                .andExpect(status().isUnauthorized())
                .andReturn();
        log.info(getUserInfo("admin"));
    }

    @Test
    @DisplayName("[Disable User] - By Other User")
    @WithUserDetails("tester")
    void byOtherUser() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/user/disable/admin"))
                .andExpect(status().isUnauthorized())
                .andReturn();
        log.info(getUserInfo("admin"));
    }

    @Test
    @DisplayName("[Disable User] - Disable myself")
    @WithUserDetails("admin")
    void disableMyself() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/user/disable/admin"))
                .andExpect(status().isOk())
                .andReturn();
        log.info(getUserInfo("admin"));
    }

    @Test
    @DisplayName("[Disable User] - Disable user by Admin")
    @WithUserDetails("admin")
    void correctRequest() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/user/disable/tester"))
                .andExpect(status().isOk())
                .andReturn();
        log.info(getUserInfo("tester"));
    }

}
