package com.perfect.community.api.service.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModificationTest extends PostServiceTest {

    long boardNoByModifyPost;
    long modifyPno;

    @BeforeEach
    void setUp() {
        boardNoByModifyPost = 1;
        modifyPno = 2;
        log.info("[Before] " + getInfoByPno(modifyPno));
    }

    @AfterEach
    void tearDown() {
        log.info("[After] " + getInfoByPno(modifyPno));
    }

    @Test
    void modification() {
        PostDTO postDTO = PostDTO.builder()
                .postNo(1)
                .boardNo(boardNoByModifyPost)
                .type(PostType.NOTICE.name())
                .title("JUNIT MODIFICATION TEST")
                .contents("JUST FOR MODIFICATION")
                .build();
        postService.modification(modifyPno, postDTO);
    }

    @Test
    void byInvalidPno() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .postNo(1)
                    .boardNo(boardNoByModifyPost)
                    .type(PostType.NOTICE.name())
                    .title("JUNIT MODIFICATION TEST")
                    .contents("JUST FOR MODIFICATION")
                    .build();
            postService.modification(-1, postDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void byInvalidBoardNo() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .postNo(1)
                    .boardNo(-1)
                    .type(PostType.NOTICE.name())
                    .title("JUNIT MODIFICATION TEST")
                    .contents("JUST FOR MODIFICATION")
                    .build();
            postService.modification(modifyPno, postDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void withoutType() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .postNo(1)
                    .boardNo(boardNoByModifyPost)
                    .title("JUNIT MODIFICATION TEST")
                    .contents("JUST FOR MODIFICATION")
                    .build();
            postService.modification(modifyPno, postDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void byNullTitle() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .postNo(1)
                    .boardNo(boardNoByModifyPost)
                    .type(PostType.NOTICE.name())
                    .title(null)
                    .contents("JUST FOR MODIFICATION")
                    .build();
            postService.modification(modifyPno, postDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void byNullContents() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .postNo(1)
                    .boardNo(boardNoByModifyPost)
                    .type(PostType.NOTICE.name())
                    .title("JUNIT MODIFICATION TEST")
                    .contents(null)
                    .build();
            postService.modification(modifyPno, postDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void byOtherUser() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .postNo(1)
                    .boardNo(boardNoByModifyPost)
                    .type(PostType.NOTICE.name())
                    .title("JUNIT MODIFICATION TEST")
                    .contents("JUST FOR MODIFICATION")
                    .build();
            postService.modification(modifyPno, postDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
