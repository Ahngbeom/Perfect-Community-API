package com.perfect.community.api.controller.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostType;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ModificationTest extends PostControllerTest {
    @Test
    @WithUserDetails("admin")
    void modification() {
        try {
            log.info("Before: " + getPostInfo(1));
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(2)
                    .type(PostType.NORMAL.name())
                    .title("JUNIT INTEGRATION TEST FOR POST MODIFICATION")
                    .contents("JUST FOR POST MODIFICATION")
                    .build();
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/post/modify/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(String.valueOf(objectMapper.valueToTree(postDTO))))
                    .andExpect(status().isOk())
                    .andReturn();
            log.info("After: " + getPostInfo(Long.parseLong(mvcResult.getResponse().getContentAsString())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithAnonymousUser
    void byAnonymous() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(2)
                .type(PostType.NORMAL.name())
                .title("JUNIT INTEGRATION TEST FOR POST MODIFICATION")
                .contents("JUST FOR POST MODIFICATION")
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/post/modify/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(objectMapper.valueToTree(postDTO))))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @WithUserDetails("tester1")
    void byOtherUser() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(2)
                .type(PostType.NORMAL.name())
                .title("JUNIT INTEGRATION TEST FOR POST MODIFICATION")
                .contents("JUST FOR POST MODIFICATION")
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/post/modify/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(objectMapper.valueToTree(postDTO))))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @WithUserDetails("admin")
    void withoutBoardNo() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .type(PostType.NORMAL.name())
                .title("JUNIT INTEGRATION TEST FOR POST MODIFICATION")
                .contents("JUST FOR POST MODIFICATION")
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/post/modify/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(objectMapper.valueToTree(postDTO))))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @WithUserDetails("admin")
    void byNegativeBoardNo() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(-1)
                .type(PostType.NORMAL.name())
                .title("JUNIT INTEGRATION TEST FOR POST MODIFICATION")
                .contents("JUST FOR POST MODIFICATION")
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/post/modify/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(objectMapper.valueToTree(postDTO))))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @WithUserDetails("admin")
    void byNullType() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(2)
                .type(null)
                .title("JUNIT INTEGRATION TEST FOR POST MODIFICATION")
                .contents("JUST FOR POST MODIFICATION")
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/post/modify/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(objectMapper.valueToTree(postDTO))))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @WithUserDetails("admin")
    void byNullTitle() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(2)
                .type(PostType.NORMAL.name())
                .title(null)
                .contents("JUST FOR POST MODIFICATION")
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/post/modify/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(objectMapper.valueToTree(postDTO))))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @WithUserDetails("admin")
    void byNullContents() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(2)
                .type(PostType.NORMAL.name())
                .title("JUNIT INTEGRATION TEST FOR POST MODIFICATION")
                .contents(null)
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/post/modify/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(objectMapper.valueToTree(postDTO))))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @WithUserDetails("admin")
    void byInvalidPostNo() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(2)
                .type(PostType.NORMAL.name())
                .title("JUNIT INTEGRATION TEST FOR POST MODIFICATION")
                .contents("JUST FOR POST MODIFICATION")
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/post/modify/-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(objectMapper.valueToTree(postDTO))))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
