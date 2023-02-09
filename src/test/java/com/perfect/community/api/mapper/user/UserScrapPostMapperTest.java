package com.perfect.community.api.mapper.user;

import com.perfect.community.api.mapper.MapperTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//@Rollback(value = false)
@DisplayName("[Mapper] User's scrap post")
public class UserScrapPostMapperTest extends MapperTest {

    @Test
    @DisplayName("Scraping")
    void scraping() {
        try {
            log.info(userScrapPostMapper.insertScrapPost("admin", 2));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("Scraped posts")
    void getAllScrapedPosts() {
        log.info(userScrapPostMapper.getAllByUserId("admin"));
    }

    @Test
    @DisplayName("delete scraped post")
    void deleteScrapedPost() {
        log.info(userScrapPostMapper.deleteByUserIdAndPostNo("tester", 1));
    }

}
