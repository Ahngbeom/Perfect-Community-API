package com.perfect.community.api.controller.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[Integrated Controller] Post modification")
public class ModificationTest extends PostControllerTest {
    @Test
    @DisplayName("By written user")
    @WithUserDetails("admin")
    void modification() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(2)
                    .type(PostType.NORMAL.name())
                    .title("JUNIT INTEGRATION TEST FOR POST MODIFICATION")
                    .contents("JUST FOR POST MODIFICATION")
                    .build();
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/post/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(String.valueOf(objectMapper.valueToTree(postDTO))))
//                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("By anonymous user")
    @WithAnonymousUser
    void byAnonymous() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(2)
                .type(PostType.NORMAL.name())
                .title("JUNIT INTEGRATION TEST FOR POST MODIFICATION")
                .contents("JUST FOR POST MODIFICATION")
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/post/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(objectMapper.valueToTree(postDTO))))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @DisplayName("By not written user")
    @WithUserDetails("tester")
    void byOtherUser() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(2)
                .type(PostType.NORMAL.name())
                .title("JUNIT INTEGRATION TEST FOR POST MODIFICATION")
                .contents("JUST FOR POST MODIFICATION")
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/post/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(objectMapper.valueToTree(postDTO))))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @DisplayName("Without board no")
    @WithUserDetails("admin")
    void withoutBoardNo() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .type(PostType.NORMAL.name())
                .title("JUNIT INTEGRATION TEST FOR POST MODIFICATION")
                .contents("JUST FOR POST MODIFICATION")
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/post/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(objectMapper.valueToTree(postDTO))))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName("With invalid board no")
    @WithUserDetails("admin")
    void byNegativeBoardNo() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(-1)
                .type(PostType.NORMAL.name())
                .title("JUNIT INTEGRATION TEST FOR POST MODIFICATION")
                .contents("JUST FOR POST MODIFICATION")
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/post/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(objectMapper.valueToTree(postDTO))))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName("With null type")
    @WithUserDetails("admin")
    void byNullType() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(2)
                .type(null)
                .title("JUNIT INTEGRATION TEST FOR POST MODIFICATION")
                .contents("JUST FOR POST MODIFICATION")
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/post/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(objectMapper.valueToTree(postDTO))))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName("With null title")
    @WithUserDetails("admin")
    void byNullTitle() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(2)
                .type(PostType.NORMAL.name())
                .title(null)
                .contents("JUST FOR POST MODIFICATION")
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/post/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(objectMapper.valueToTree(postDTO))))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName("With null contents")
    @WithUserDetails("admin")
    void byNullContents() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(2)
                .type(PostType.NORMAL.name())
                .title("JUNIT INTEGRATION TEST FOR POST MODIFICATION")
                .contents(null)
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/post/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(objectMapper.valueToTree(postDTO))))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName("Invalid post no")
    @WithUserDetails("admin")
    void byInvalidPostNo() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(2)
                .type(PostType.NORMAL.name())
                .title("JUNIT INTEGRATION TEST FOR POST MODIFICATION")
                .contents("JUST FOR POST MODIFICATION")
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/post/-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(objectMapper.valueToTree(postDTO))))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }
}
