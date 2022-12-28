package com.perfect.community.api.controller.user;

import com.perfect.community.api.dto.user.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateUserInfoTest extends UserControllerTest {

    String requestBody;

    @Test
    @DisplayName("[Update User Info] - Not logged in")
    void notLogin() throws Exception {
        try {
            requestBody = objectMapper.valueToTree(
                    UserDTO.builder()
                            .userId("admin")
                            .nickname("A")
                            .build()
            ).toString();
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/user/update")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isUnauthorized())
                    .andReturn();
            log.error(mvcResult.getResponse().getContentAsString());
        } catch (NestedServletException e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("[Update User Info] - By Other User")
    @WithUserDetails("tester1")
    void byOtherUser() throws Exception {
        requestBody = objectMapper.valueToTree(
                UserDTO.builder()
                        .userId("admin")
                        .nickname("A")
                        .build()
        ).toString();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnauthorized())
                .andReturn();
        log.error(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("[Update User Info] - Correct Request")
    @WithUserDetails("admin")
    void updateMyself() throws Exception {
        requestBody = objectMapper.valueToTree(
                UserDTO.builder()
                        .userId("admin")
                        .nickname("A")
                        .build()
        ).toString();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();
        String updatedUserId = mvcResult.getResponse().getContentAsString();
        log.info(updatedUserId);
        log.info(getUserInfo(updatedUserId));
    }
}
