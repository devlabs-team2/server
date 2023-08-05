package com.dlteam2.server.User.Entity;

import com.dlteam2.server.User.Etc.LoginType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginInfoId implements Serializable {
    @Column(name="login_type")
    private LoginType loginType;
    private UUID id; //회원 id

    @Builder
    public LoginInfoId(LoginType loginType, UUID id) {
        this.loginType = loginType;
        this.id = id;
    }
}
