package com.dlteam2.server.User.DTO;

import com.dlteam2.server.User.Entity.Terms;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TermsDTO {
    private UUID id;
    private Boolean terms;

    @Builder
    public TermsDTO(UUID id, Boolean terms) {
        this.id = id;
        this.terms = terms;
    }

    public Terms toEntity(){
        return Terms.builder()
                .id(this.id)
                .terms(this.terms).build();
    }
}
