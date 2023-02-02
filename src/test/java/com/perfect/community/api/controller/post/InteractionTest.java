package com.perfect.community.api.controller.post;

import com.perfect.community.api.controller.ControllerIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[Integrated Controller] Post's Interaction")
public class InteractionTest extends ControllerIntegrationTest {

    @Autowired
    protected PostInteractionController controller;

    @Test
    @DisplayName("Increase views")
    void increaseViews() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/post/views/" + 1))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("Increase recommend")
    @WithUserDetails("admin")
    void increaseRecommend() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/post/recommend/" + 1))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("Increase not recommend")
    @WithUserDetails("admin")
    void increaseNotRecommend() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/post/not-recommend/" + 1))
                .andExpect(status().isOk())
                .andReturn();
    }

}
