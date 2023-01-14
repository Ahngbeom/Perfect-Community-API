package com.perfect.community.api.controller.user;

import com.perfect.community.api.controller.ControllerIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserScrapPostControllerTest extends ControllerIntegrationTest {

    @BeforeEach
    void setUp() {
        setUpWithWebAppCtx();
    }

    @Test
    void getScrapedPosts() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/scraped-posts"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithAnonymousUser
    void getScrapedPostsByAnonymous() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/scraped-posts"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    void scrapPost() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/scrap-post/2"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void releaseScrapedPost() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/release-scraped-post/1"))
                .andExpect(status().isOk())
                .andReturn();
    }
}