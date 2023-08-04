package com.dlteam2.server.User.Entity;

import com.dlteam2.server.User.Etc.LoginType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginInfoId implements Serializable {
    @Column(name="login_type")
    private LoginType loginType;
    private String id;

    @Builder
    public LoginInfoId(LoginType loginType, String id) {
        this.loginType = loginType;
        this.id = id;
    }
}
