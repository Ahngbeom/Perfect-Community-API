package com.perfect.community.api.mapper.user;

import com.perfect.community.api.mapper.MapperTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

@Rollback(value = false)
public class UserPostScrapMapperTest extends MapperTest {

    @Autowired
    private UserPostScrapMapper mapper;

    @Test
    void scraping() {
        try {
            log.info(mapper.insertScrapPost("admin", 2));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void getAllScrapedPosts() {
        log.info(mapper.getAllByUserId("admin"));
    }

    @Test
    void deleteScrapedPost() {
        log.info(mapper.deleteByUserIdAndPostNo("tester", 1));
    }

}
