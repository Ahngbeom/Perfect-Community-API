package com.board.api.service.post;

import com.board.api.dto.post.PostDTO;
import com.board.api.dto.post.PostListOptDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/securityContext.xml"})
class PostServiceImplTest {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private PostService service;
    @Autowired
    private PostJoinUserService postJoinUserService;
    @Autowired
    private PostUtils postUtils;

    @BeforeEach
    void setUp() {
        assertNotNull(log);
        assertNotNull(service);
    }

//    @AfterEach
//    void resultVerify() {
//        service.getPostList(new PostListOptDTO(1)).forEach(log::info);
//    }

    @Test
    void getPostList() {
        service.getPostList(new PostListOptDTO(1)).forEach(log::info);
    }

    @Test
    void getPostListWithUserDetails() {
        postJoinUserService.getPostListWithUserDetails(new PostListOptDTO(1)).forEach(log::info);
    }

    @Test
    void getInfoByPno() {
        PostDTO postDTO = service.getInfoByPno(9);
        log.info(postDTO);
    }

    @Test
//    @Transactional
    void registration() {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(1)
                .type("NORMAL")
                .title("JUNIT TEST")
                .contents("THIS POST FOR JUNIT TEST")
                .build();
        log.info(service.registration("admin", postDTO));
    }

    @Test
    void modification() {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(1)
                .pno(8)
                .type("notice")
                .title("JUNIT TEST")
                .contents("THIS POST FOR JUNIT TEST")
                .build();
        service.modification("admin", postDTO);
        log.info(service.getInfoByPno(8));
    }

    @Test
    void remove() {
        service.remove("admin", 9);
        getPostList();
    }

    @Test
    void countPosts() {
        log.info(postUtils.countPosts(1));
    }

    @Test
    void checkPostVerification() {

    }

}