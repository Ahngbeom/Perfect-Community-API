package com.perfect.community.api.controller.post;

import com.fasterxml.jackson.core.type.TypeReference;
import com.perfect.community.api.dto.post.PostDTO;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InfoTest extends PostControllerTest {
    @Test
    void info() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/post/info/1"))
                .andExpect(status().isOk())
                .andReturn();
        log.info(objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<PostDTO>() {
        }));
    }

    @Test
    void byInvalidPno() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/post/info/-1"))
                .andExpect(status().isBadRequest())
                .andReturn();
        log.error(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void notPathVariableForPostNo() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/post/info"))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
