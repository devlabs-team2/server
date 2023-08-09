package com.dlteam2.server.User.DTO.ResponseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoResponseDTO {
    private String mobile;
    private String grade;

    @JsonProperty("login_info")
    private List<LoginInfo> loginInfo;
    @Builder
    public static class LoginInfo{
        @JsonProperty("email")
        private String email;
        @JsonProperty("login_type")
        private String loginType;
    }

    @Builder
    public UserInfoResponseDTO(String mobile, String grade, List<LoginInfo> loginInfo) {
        this.mobile = mobile;
        this.grade = grade;
        this.loginInfo = loginInfo;
    }
}
