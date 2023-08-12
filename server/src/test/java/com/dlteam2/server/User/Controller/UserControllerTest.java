package com.dlteam2.server.User.Controller;

import com.dlteam2.server.Common.Constants;
import com.dlteam2.server.Common.ControllerTest;
import com.dlteam2.server.User.DTO.ResponseDTO.UserInfoResponseDTO;
import com.dlteam2.server.User.Etc.Grade;
import com.dlteam2.server.User.Etc.LoginType;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends ControllerTest {

    @DisplayName("이메일 찾기 테스트")
    @Test
    @WithMockUser("user")
    void searchEmail() throws Exception{
        given(userService.findIdByMobile(Constants.test_user_1_mobile)).willReturn(Constants.test_user_1_id);
        given(userService.findEmail(Constants.test_user_1_id)).willReturn(Constants.test_user_1_email);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile", Constants.test_user_1_mobile);

        mockMvc.perform(post("/users/search/email")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(jsonObject)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.email").value(Constants.test_user_1_email));
    }

    @DisplayName("회원 정보 찾기 테스트")
    @Test
    @WithMockUser("user")
    void getUserInfo() throws Exception{
        given(userService.getUserId(Constants.test_user_1_token)).willReturn(Constants.test_user_1_id);
        given(userService.getUserInfo(Constants.test_user_1_id)).willReturn(UserInfoResponseDTO.builder()
                .loginInfo(List.of(UserInfoResponseDTO.LoginInfo.builder()
                        .loginType(LoginType.BASIC.name())
                        .email(Constants.test_user_1_email).build()))
                .grade(Grade.BASIC.name())
                .mobile(Constants.test_user_1_mobile)
                .build());

        mockMvc.perform(get("/users/info")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", Constants.test_user_1_token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.login_info[0].email").value(Constants.test_user_1_email));
    }

    @DisplayName("휴대폰 번호 변경 테스트")
    @Test
    @WithMockUser("user")
    void updateMobile() throws Exception{
        given(userService.getUserId(Constants.test_user_1_token)).willReturn(Constants.test_user_1_id);
        given(userService.updateMobile(Constants.test_user_1_id, Constants.test_user_1_mobile)).willReturn(true);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile", Constants.test_user_1_mobile);

        mockMvc.perform(patch("/users/info/update/mobile")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", Constants.test_user_1_token)
                .content(objectMapper.writeValueAsString(jsonObject)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value("SUCCESS"));
    }

    @DisplayName("이메일 변경 테스트")
    @Test
    @WithMockUser("user")
    void updateEmail() throws Exception{
        given(userService.getUserId(Constants.test_user_1_token)).willReturn(Constants.test_user_1_id);
        given(userService.updateEmail(Constants.test_user_1_id, Constants.test_user_1_email)).willReturn(true);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", Constants.test_user_1_email);

        mockMvc.perform(patch("/users/info/update/email")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", Constants.test_user_1_token)
                        .content(objectMapper.writeValueAsString(jsonObject)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value("SUCCESS"));
    }

    @DisplayName("비밀번호 변경 테스트")
    @Test
    @WithMockUser("user")
    void updatePassword() throws Exception{
        given(userService.getUserId(Constants.test_user_1_token)).willReturn(Constants.test_user_1_id);
        given(userService.updatePassword(Constants.test_user_1_id, Constants.test_user_1_password)).willReturn(true);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("password", Constants.test_user_1_password);

        mockMvc.perform(patch("/users/info/update/password")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", Constants.test_user_1_token)
                        .content(objectMapper.writeValueAsString(jsonObject)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value("SUCCESS"));
    }
}