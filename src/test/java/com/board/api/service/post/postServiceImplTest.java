package com.board.api.service.post;

import com.board.api.dto.post.PostDTO;
import com.board.api.dto.post.PostListOptDTO;
import com.board.api.mapper.AuthMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
class postServiceImplTest {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private PostCRUD_Service service;

    @InjectMocks
    @Autowired
    private AuthMapper authMapper;

    @BeforeEach
    void setUp() {
        assertNotNull(log);
        assertNotNull(service);
    }

    @Test
    void getPostList() {
        service.getPostList(new PostListOptDTO());
    }

    @Test
    void getInfoByPno() {
    }

    @Test
    @Transactional
    void registration() {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(1)
                .type("NORMAL")
                .title("JUNIT TEST")
                .contents("THIS POST FOR JUNIT TEST")
                .build();
        service.registration("admin", postDTO);
        log.info(postDTO);
    }

    @Test
    void modification() {
    }

    @Test
    void remove() {
    }

    @Test
    void removeAll() {
    }

    @Test
    void countPosts() {
    }

    @Test
    void initPnoValue() {
    }

    @Test
    void checkPostVerification() {
    }

    @Test
    void verifyPostType() {
        log.info(service.verifyPostType("notice"));
    }
}