package com.dlteam2.server.User.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {
    @EmbeddedId
    private RefreshTokenId id;
    private String token;
    private Date iat; //발행일
    private boolean exp;


    @Builder
    public RefreshToken(RefreshTokenId id, String token, Date iat, boolean exp) {
        this.id = id;
        this.token = token;
        this.iat = iat;
        this.exp = exp;
    }
}
