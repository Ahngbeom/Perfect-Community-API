package com.perfect.community.api.controller.user;

import com.perfect.community.api.controller.ControllerIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[User's scrap post]")
class UserScrapPostControllerTest extends ControllerIntegrationTest {

    @Test
    @DisplayName("[User's scrap post] - My scraped posts")
    @WithUserDetails("admin")
    void getScrapedPosts() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/scraped-posts"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("[User's scrap post] - My scraped posts by Anonymous")
    @WithAnonymousUser
    void getScrapedPostsByAnonymous() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/scraped-posts"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @DisplayName("[User's scrap post] - Scrap post")
    void scrapPost() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/scrap-post/2"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("[User's scrap post] - Scrap post by anonymous")
    @WithAnonymousUser
    void scrapPostByAnonymous() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/scrap-post/2"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @DisplayName("[User's scrap post] - Release scraped post")
    void releaseScrapedPost() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/release-scraped-post/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("[User's scrap post] - Release scraped post by anonymous")
    @WithAnonymousUser
    void releaseScrapedPostByAnonymous() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/release-scraped-post/1"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }
}