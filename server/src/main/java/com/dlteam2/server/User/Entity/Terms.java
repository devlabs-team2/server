package com.dlteam2.server.User.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms {
    @Id
    private String id;
    private Boolean terms;

    @Builder
    public Terms(String id, Boolean terms) {
        this.id = id;
        this.terms = terms;
    }
}
