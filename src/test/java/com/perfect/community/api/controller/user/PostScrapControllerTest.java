package com.perfect.community.api.controller.user;

import com.perfect.community.api.controller.ControllerIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[Integrated Controller] Post scrap")
class PostScrapControllerTest extends ControllerIntegrationTest {

    @Test
    @DisplayName("My scraped posts")
    @WithUserDetails("admin")
    void getScrapedPosts() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/post/scraped"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("My scraped posts by Anonymous")
    @WithAnonymousUser
    void getScrapedPostsByAnonymous() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/post/scraped"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @DisplayName("Scrap post")
    void scrapPost() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/post/scrap/3"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("Scrap post by anonymous")
    @WithAnonymousUser
    void scrapPostByAnonymous() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/post/scrap/3"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @DisplayName("Release scraped post")
    void releaseScrapedPost() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/post/release-scrap/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("Release scraped post by anonymous")
    @WithAnonymousUser
    void releaseScrapedPostByAnonymous() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/post/release-scrap/1"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }
}