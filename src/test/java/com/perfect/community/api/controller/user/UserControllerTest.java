package com.perfect.community.api.controller.user;

import com.perfect.community.api.controller.ControllerIntegrationTest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends ControllerIntegrationTest {

    public String getUserInfo(String userId) throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/" + userId))
                .andReturn();
        return mvcResult.getResponse().getContentAsString();
    }

    public boolean verifyPassword(String password) throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/verify-password")
                        .param("password", password))
                .andExpect(status().isOk())
                .andReturn();
        return Boolean.parseBoolean(mvcResult.getResponse().getContentAsString());
    }

}