package com.dlteam2.server.User.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms {
    @Id
    private UUID id; //회원 id
    private Boolean terms;

    @Builder
    public Terms(UUID id, Boolean terms) {
        this.id = id;
        this.terms = terms;
    }
}
