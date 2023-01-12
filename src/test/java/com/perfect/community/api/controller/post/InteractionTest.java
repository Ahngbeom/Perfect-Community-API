package com.perfect.community.api.controller.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.perfect.community.api.controller.ControllerIntegrationTest;
import com.perfect.community.api.dto.post.PostRecommendDTO;
import com.perfect.community.api.dto.post.PostViewsDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InteractionTest extends ControllerIntegrationTest {
    @Autowired
    protected PostInteractionController controller;

    @BeforeEach
    void setUp() {
        setUp(controller);
    }

    @Test
    void increaseViews() throws Exception {
        String json = objectMapper.writeValueAsString(PostViewsDTO.builder().postNo(1).build());
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/post/views")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithUserDetails("admin")
    void increaseRecommend() throws Exception {
        String json = objectMapper.writeValueAsString(PostRecommendDTO.builder().postNo(1).build());
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/post/recommend")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithUserDetails("admin")
    void increaseNotRecommend() throws Exception {
        String json = objectMapper.writeValueAsString(PostRecommendDTO.builder().postNo(1).build());
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/post/not-recommend")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();
    }

}
