package com.perfect.community.api.controller.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.perfect.community.api.dto.user.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ListTest extends UserControllerTest {
    @Test
    @DisplayName("[User's List]")
    void list() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/list"))
                .andExpect(status().isOk())
                .andReturn();
        log.info(objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<UserDTO>>(){}));
    }
}
