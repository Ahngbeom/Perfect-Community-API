package com.perfect.community.api.controller.user;

import com.perfect.community.api.dto.user.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[Update User Info]")
public class UpdateUserInfoTest extends UserControllerTest {

    @Test
    @DisplayName("[Update User Info] - Authenticated")
    @WithUserDetails("admin")
    void updateMyself() throws Exception {
        String requestBody = objectMapper.valueToTree(
                UserDTO.builder()
                        .nickname("A")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/user/admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();
        String updatedUserId = mvcResult.getResponse().getContentAsString();
        log.info(getUserInfo(updatedUserId));
    }

    @Test
    @DisplayName("[Update User Info] - By Anonymous")
    @WithAnonymousUser
    void notLogin() throws Exception {
        String requestBody = objectMapper.valueToTree(
                UserDTO.builder()
                        .nickname("A")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/user/admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnauthorized())
                .andReturn();

    }

    @Test
    @DisplayName("[Update User Info] - By Other User")
    @WithUserDetails("tester")
    void byOtherUser() throws Exception {
        String requestBody = objectMapper.valueToTree(
                UserDTO.builder()
                        .nickname("A")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/user/admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }
}
