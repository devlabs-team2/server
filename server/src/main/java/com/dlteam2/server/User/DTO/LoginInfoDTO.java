package com.dlteam2.server.User.DTO;

import com.dlteam2.server.User.Entity.LoginInfo;
import com.dlteam2.server.User.Entity.LoginInfoId;
import com.dlteam2.server.User.Entity.User;
import com.dlteam2.server.User.Etc.LoginType;
import com.dlteam2.server.User.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginInfoDTO {
    private LoginType loginType;
    private String id; //식별 id
    private String email;
    private String userId; //회원 id(social 로그인 고유 아이디)
    private String password;

    @Builder
    public LoginInfoDTO(LoginType loginType, String id, String email, String userId, String password) {
        this.loginType = loginType;
        this.id = id;
        this.email = email;
        this.userId = userId;
        this.password = password;
    }

}
