package com.dlteam2.server.User.Entity;

import com.dlteam2.server.User.DTO.LoginInfoDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginInfo {
    @EmbeddedId
    private LoginInfoId id;
    private String email;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;
    private String password;

    @Builder
    public LoginInfo(LoginInfoId id, String email, User user, String password) {
        this.id = id;
        this.email = email;
        this.user = user;
        this.password = password;
    }

    public void updatePassword(LoginInfoDTO loginInfoDTO){
        this.password = loginInfoDTO.getPassword();
    }
}
