package com.perfect.community.api.mapper.user;

import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;

@Rollback(value = false)
public class UserScrapPostMapperTest extends UsersMapperTest {

    @Test
    void scraping() {
        try {
            log.info(userScrapPostMapper.insertScrapPost("admin", 2));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void getAllScrapedPosts() {
        log.info(userScrapPostMapper.getAllByUserId("admin"));
    }

    @Test
    void deleteScrapedPost() {
        log.info(userScrapPostMapper.deleteByUserIdAndPostNo("tester", 1));
    }

}
