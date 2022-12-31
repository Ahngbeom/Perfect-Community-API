package com.perfect.community.api.service.post;

import com.perfect.community.api.dto.board.BoardDTO;
import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostListOptDTO;
import com.perfect.community.api.service.utils.RelocateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/securityContext.xml",
        "file:web/WEB-INF/interceptor-servlet.xml",
})
@Transactional
class PostServiceTest {

    protected static final Logger log = LogManager.getLogger();

    @Autowired
    protected PostService postService;

    @Autowired
    protected PostJoinUserService postJoinUserService;

    @Autowired
    protected PostUtils postUtils;

    @Autowired
    private RelocateService relocateService;

    @BeforeEach
    void setUp() {
        assertNotNull(log);
        assertNotNull(postService);
        assertNotNull(postJoinUserService);
        assertNotNull(postUtils);
    }

    @AfterTransaction
    void relocatePno() {
        log.info("auto_increment key: " + relocateService.relocateBoardNumbers("posts"));
    }

    @Test
    void getInfoByPno() {
        PostDTO postDTO = postService.getInfoByPno(9);
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
        log.info(postService.registration("admin", postDTO));
    }

    @Test
    void modification() {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(1)
                .type("notice")
                .title("JUNIT TEST")
                .contents("THIS POST FOR JUNIT TEST")
                .build();
        postService.modification(8, "admin", postDTO);
        log.info(postService.getInfoByPno(8));
    }

    @Test
    void remove() {
        postService.remove("admin", 9);
    }

    @Test
    void countPosts() {
//        log.info(postUtils.countPosts(1));
    }

    @Test
    void checkPostVerification() {

    }

}