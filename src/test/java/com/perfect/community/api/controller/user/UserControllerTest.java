package com.perfect.community.api.controller.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.perfect.community.api.controller.ControllerIntegrationTest;
import com.perfect.community.api.dto.user.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends ControllerIntegrationTest {

    @Autowired
    private UserController controller;

    @BeforeEach
    void setUp() {
        setUp(controller);
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



    @Test
    void verifyPassword() {
    }

    @Test
    void userIdDuplicatesChecking() {
    }

    @Test
    void usernameDuplicatesChecking() {
    }
}