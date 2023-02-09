package com.perfect.community.api.mapper.user;

import com.perfect.community.api.mapper.MapperTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//@Rollback(value = false)
@DisplayName("[Mapper] User's scrap post")
public class PostScrapMapperTest extends MapperTest {

    @Test
    @DisplayName("Scraping")
    void scraping() {
        try {
            log.info(postScrapMapper.insertScrapPost("admin", 2));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("Scraped posts")
    void getAllScrapedPosts() {
        log.info(postScrapMapper.getAllByUserId("admin"));
    }

    @Test
    @DisplayName("delete scraped post")
    void deleteScrapedPost() {
        log.info(postScrapMapper.deleteByUserIdAndPostNo("tester", 1));
    }

}
