package com.perfect.community.api.controller.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[Withdraw User]")
public class WithdrawTest extends UserControllerTest {

    @Test
    @DisplayName("[Withdraw User] - Myself")
    @WithUserDetails("tester")
    void withdraw() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/tester"))
                    .andExpect(status().isOk())
                    .andReturn();
    }

    @Test
    @DisplayName("[Withdraw User] - By other user")
    @WithUserDetails("tester")
    void withdrawByOtherUser() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/admin"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }
    
    @Test
    @DisplayName("[Withdraw User] - By anonymous")
    @WithAnonymousUser
    void notLogin() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/tester"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

}
