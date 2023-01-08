package com.perfect.community.api.controller.post;

import com.perfect.community.api.dto.post.PostFilterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ListTest extends PostControllerTest {
    @Test
    void list() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/post"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void listWithOptions() throws Exception {
        String options = objectMapper.valueToTree(
                PostFilterDTO.builder()
                        .boardNo(1)
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(options))
                .andExpect(status().isOk())
                .andReturn();
    }
}
