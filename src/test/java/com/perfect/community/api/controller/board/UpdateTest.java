package com.perfect.community.api.controller.board;

import com.perfect.community.api.dto.board.BoardDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[Board's Update]")
class UpdateTest extends BoardControllerTest {

    String requestBody;

    @Test
    @DisplayName("[Board's Update] - By created user")
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
    @DisplayName("[Board's Update] - Invalid board no")
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
    @DisplayName("[Board's Update] - With null title")
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
    @DisplayName("[Board's Update] - Access denied by not admin user")
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
    @DisplayName("[Board's Update] - Access denied by anonymous")
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