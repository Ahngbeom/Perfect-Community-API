package com.perfect.community.api.controller.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[Post's Register]")
public class RegistrationTest extends PostControllerTest {

    @Test
    @DisplayName("[Post's Register] - By authenticated user")
    public void registration() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(1)
                .type(PostType.NORMAL.name())
                .title("JUNIT INTEGRATION TEST FOR POST REGISTRATION")
                .contents("JUST FOR POST REGISTRATION")
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.valueToTree(postDTO).toString()))
                .andExpect(status().isOk())
                .andReturn();
        log.info(getPostInfo(Long.parseLong(mvcResult.getResponse().getContentAsString())));
    }

    @Test
    @DisplayName("[Post's Register] - By anonymous")
    @WithAnonymousUser
    void ByAnonymous() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .boardNo(1)
                .type(PostType.NORMAL.name())
                .title("JUNIT INTEGRATION TEST FOR POST REGISTRATION")
                .contents("JUST FOR POST REGISTRATION")
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.valueToTree(postDTO).toString()))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @DisplayName("[Post's Register] - Without board no")
    @WithUserDetails("admin")
    void withoutBoardNo() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .type(PostType.NORMAL.name())
                    .title("JUNIT INTEGRATION TEST FOR POST REGISTRATION")
                    .contents("JUST FOR POST REGISTRATION")
                    .build();
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.valueToTree(postDTO).toString()))
                    .andExpect(status().isBadRequest())
                    .andReturn();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("[Post's Register] - invalid board no")
    @WithUserDetails("admin")
    void invalidBoardNo() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(-1)
                    .type(PostType.NORMAL.name())
                    .title("JUNIT INTEGRATION TEST FOR POST REGISTRATION")
                    .contents("JUST FOR POST REGISTRATION")
                    .build();
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.valueToTree(postDTO).toString()))
                    .andExpect(status().isBadRequest())
                    .andReturn();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("[Post's Register] - With null type")
    @WithUserDetails("admin")
    void ByNullType() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(1)
                    .type(null)
                    .title("JUNIT INTEGRATION TEST FOR POST REGISTRATION")
                    .contents("JUST FOR POST REGISTRATION")
                    .build();
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.valueToTree(postDTO).toString()))
                    .andExpect(status().isBadRequest())
                    .andReturn();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("[Post's Register] - With null title")
    @WithUserDetails("admin")
    void ByNullTitle() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(1)
                    .type(PostType.NORMAL.name())
                    .title(null)
                    .contents("JUST FOR POST REGISTRATION")
                    .build();
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.valueToTree(postDTO).toString()))
                    .andExpect(status().isBadRequest())
                    .andReturn();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    @Test
    @DisplayName("[Post's Register] - With null contents")
    @WithUserDetails("admin")
    void ByNullContents() {
        try {
            PostDTO postDTO = PostDTO.builder()
                    .boardNo(1)
                    .type(PostType.NORMAL.name())
                    .title("JUNIT INTEGRATION TEST FOR POST REGISTRATION")
                    .contents(null)
                    .build();
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.valueToTree(postDTO).toString()))
                    .andExpect(status().isBadRequest())
                    .andReturn();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
