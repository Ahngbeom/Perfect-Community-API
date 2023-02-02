package com.perfect.community.api.controller.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[Integrated Controller] Enable user")
public class EnableTest extends UserControllerTest {

    @Test
    @DisplayName("Myself")
    @WithUserDetails("admin")
    void enableMySelf() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/user/enable/admin"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("By administrator")
    @WithUserDetails("admin")
    void enableByAdmin() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/user/enable/tester"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("By unauthorized user")
    @WithUserDetails("tester")
    void enableByOtherUser() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/user/enable/admin"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @DisplayName("By anonymous user")
    @WithAnonymousUser
    void enableByAnonymous() throws Exception {
           mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/user/enable/admin"))
                    .andExpect(status().isUnauthorized())
                    .andReturn();
    }

}
