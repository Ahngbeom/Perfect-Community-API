package jwt;

import com.perfect.community.api.controller.ControllerIntegrationTest;
import com.perfect.community.api.dto.jwt.TokenDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockCookie;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[JWT TEST]")
public class JwtTest extends ControllerIntegrationTest {

//    void registration(String accessToken) throws Exception {
//        PostDTO postDTO = PostDTO.builder()
//                .boardNo(1)
//                .type(PostType.NORMAL.name())
//                .title("JUNIT INTEGRATION TEST FOR POST REGISTRATION")
//                .contents("JUST FOR POST REGISTRATION")
//                .build();
//        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
//                        .header("Authorization", accessToken)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.valueToTree(postDTO).toString()))
//                .andExpect(status().isOk())
//                .andReturn();
//    }

    @Test
    @DisplayName("[JWT TEST] - JSON Authentication")
    @WithAnonymousUser
    void Login() throws Exception {
        Map<String, String> usernamePassword = new HashMap<>();
        usernamePassword.put("username", "admin");
        usernamePassword.put("password", "admin");
        String jsonData = objectMapper.writeValueAsString(usernamePassword);
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("[JWT TEST] - Reissue JWT")
    @WithAnonymousUser
    void Reissue() throws Exception {
        Map<String, String> usernamePassword = new HashMap<>();
        usernamePassword.put("username", "admin");
        usernamePassword.put("password", "admin");
        String jsonData = objectMapper.writeValueAsString(usernamePassword);
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isOk())
                .andReturn();

        TokenDTO tokenByLoggedIn = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), TokenDTO.class);
        log.info(tokenByLoggedIn);

        MockCookie mockCookie = new MockCookie("refresh-token", tokenByLoggedIn.getRefreshToken());
        mockCookie.setHttpOnly(true);
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/jwt/reissue")
                        .header("Authorization", tokenByLoggedIn.getAccessToken())
                        .cookie(mockCookie))
                .andExpect(status().isOk())
                .andReturn();
    }
}
