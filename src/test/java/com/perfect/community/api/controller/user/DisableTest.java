package com.perfect.community.api.controller.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DisableTest extends UserControllerTest {

    @Test
    @DisplayName("[Disable User] - Not logged in")
    void notLogin() {
        try {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/user/disable/admin"))
                    .andExpect(status().isUnauthorized())
                    .andReturn();
            log.error(mvcResult.getResponse().getErrorMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("[Disable User] - By Other User")
    @WithUserDetails("tester1")
    void byOtherUser() {
        try {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/user/disable/admin"))
                    .andExpect(status().isUnauthorized())
                    .andReturn();
            log.error(mvcResult.getResponse().getErrorMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("[Disable User] - Correct Request")
    @WithUserDetails("admin")
    void correctRequest() throws Exception {
        try {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/user/disable/tester1"))
                    .andExpect(status().isOk())
                    .andReturn();
            String disabledUserId = mvcResult.getResponse().getContentAsString();
            log.info(disabledUserId);
            log.info(getUserInfo(disabledUserId));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
