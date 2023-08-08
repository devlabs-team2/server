package com.dlteam2.server.User.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshTokenId implements Serializable {
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;
    private String id;

    @Builder
    public RefreshTokenId(User user, String id) {
        this.user = user;
        this.id = id;
    }
}
