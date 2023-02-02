package com.perfect.community.api.controller.board;

import com.perfect.community.api.dto.board.BoardDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[Integrated Controller] Update board")
class UpdateTest extends BoardControllerTest {

    String requestBody;

    @Test
    @DisplayName("By created user")
    @WithUserDetails("admin")
    void updateBoard() throws Exception {
        requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title("Updating")
                        .comment("Updating a board for testing in JUNIT")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/board/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();
        log.info(getBoardInfo(Long.parseLong(mvcResult.getResponse().getContentAsString())));
    }

    @Test
    @DisplayName("If board no invalid")
    @WithUserDetails("admin")
    void updateBoardWithInvalidBoardNo() throws Exception {
        requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title("Updating")
                        .comment("Updating a board for testing in JUNIT")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/board/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @DisplayName("If title is null")
    @WithUserDetails("admin")
    void updateBoardWithNullTitle() {
        try {
            requestBody = objectMapper.valueToTree(
                    BoardDTO.builder()
                            .title(null)
                            .comment("Updating a board for testing in JUNIT")
                            .build()
            ).toString();
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/board/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isBadRequest())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("By unauthorized user")
    @WithUserDetails("tester")
    void updateBoardWithUnauthorizedUser() throws Exception {
        requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title("Updating")
                        .comment("Updating a board for testing in JUNIT")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/board/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @DisplayName("By anonymous user")
    @WithAnonymousUser
    void updateBoardWithAnonymous() throws Exception {
        requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title("Updating")
                        .comment("Updating a board for testing in JUNIT")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/board/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }
}