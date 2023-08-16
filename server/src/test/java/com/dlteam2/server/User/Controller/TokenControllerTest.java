package com.dlteam2.server.User.Controller;

import com.dlteam2.server.Common.Constants;
import com.dlteam2.server.Common.ControllerTest;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TokenControllerTest extends ControllerTest {

    @DisplayName("access token 갱신 테스트")
    @Test
    @WithMockUser("user")
    void refreshAccessTokenTest() throws Exception{
        String tmpToken = "Bearer tmp";
        given(tokenService.refreshAccessToken(tmpToken)).willReturn(Constants.test_user_1_token);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("refresh_token", tmpToken);

        mockMvc.perform(post("/tokens/refresh")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(jsonObject)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.access_token").value(Constants.test_user_1_token));

    }
}
