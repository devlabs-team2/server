package com.dlteam2.server.User.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshTokenId implements Serializable {
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;
    private UUID id;

    @Builder
    public RefreshTokenId(User user, UUID id) {
        this.id = id;
        this.user = user;
    }
}
