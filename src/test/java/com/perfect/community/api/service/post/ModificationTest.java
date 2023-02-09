package com.perfect.community.api.service.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[Service] Post's modification")
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
    @DisplayName("Modification")
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
    @DisplayName("Invalid post no")
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
    @DisplayName("Invalid board no")
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
    @DisplayName("Without post type")
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
    @DisplayName("title is null")
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
    @DisplayName("contents is null")
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

//    @Test
//    @DisplayName("")
//    void byOtherUser() {
//        try {
//            PostDTO postDTO = PostDTO.builder()
//                    .postNo(1)
//                    .boardNo(boardNoByModifyPost)
//                    .type(PostType.NOTICE.name())
//                    .title("JUNIT MODIFICATION TEST")
//                    .contents("JUST FOR MODIFICATION")
//                    .build();
//            postService.modification(modifyPno, postDTO);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
