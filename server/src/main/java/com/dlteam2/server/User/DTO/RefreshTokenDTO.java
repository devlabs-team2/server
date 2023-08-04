package com.dlteam2.server.User.DTO;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshTokenDTO {
    private String id;
    private String userId;
    private Date iat;
    private boolean exp;

    @Builder
    public RefreshTokenDTO(String id, String userId, Date iat, boolean exp) {
        this.id = id;
        this.userId = userId;
        this.iat = iat;
        this.exp = exp;
    }
}
