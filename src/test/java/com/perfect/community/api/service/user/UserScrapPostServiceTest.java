package com.perfect.community.api.service.user;

import com.perfect.community.api.service.ServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserScrapPostServiceTest extends ServiceTest {

    @Autowired
    private UserScrapPostService service;

    @Test
    void scrapePost() {
        try {
            service.scrapePost("aaaaa", 1);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void getAllScrapedPosts() {
        log.info(service.getAllScrapedPosts("admin"));
    }

    @Test
    void releaseScrapedPost() {
        try {
            service.releaseScrapedPost("admin", 1);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}