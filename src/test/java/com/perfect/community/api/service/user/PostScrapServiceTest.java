package com.perfect.community.api.service.user;

import com.perfect.community.api.service.ServiceTest;
import com.perfect.community.api.service.post.PostScrapService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("[Service] Post's scrap")
class PostScrapServiceTest extends ServiceTest {

    @Autowired
    private PostScrapService service;

    @Test
    @DisplayName("Scrap")
    void scrapePost() {
        try {
            service.scrapePost("admin", 3);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("Get scraped posts")
    void getAllScrapedPosts() {
        log.info(service.getAllScrapedPosts("admin"));
    }

    @Test
    @DisplayName("Release scraped post")
    void releaseScrapedPost() {
        try {
            service.releaseScrapedPost("admin", 1);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}