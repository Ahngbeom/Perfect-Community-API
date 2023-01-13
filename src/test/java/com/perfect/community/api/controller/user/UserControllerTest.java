package com.perfect.community.api.controller.user;

import com.perfect.community.api.controller.ControllerIntegrationTest;
import com.perfect.community.api.dto.user.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends ControllerIntegrationTest {

    @Autowired
    private UserController controller;

    @BeforeEach
    void setUp() {
        setUpWithController(controller);
    }

    public UserDTO getUserInfo(String userId) throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/info")
                        .param("userId", userId))
//                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        if (mvcResult.getResponse().getContentAsString().isEmpty())
            return null;
        return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserDTO.class);
    }

    public boolean verifyPassword(String password) throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/verify-password")
                        .param("password", password))
                .andExpect(status().isOk())
                .andReturn();
        return Boolean.parseBoolean(mvcResult.getResponse().getContentAsString());
    }

}