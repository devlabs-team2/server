package com.dlteam2.server.User.DTO;

import com.dlteam2.server.User.Entity.User;
import com.dlteam2.server.User.Etc.Grade;
import com.dlteam2.server.User.Etc.Role;
import lombok.*;

import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDTO {
    private Grade grade;
    private String mobile;
    private Role role;

    @Builder
    public UserDTO(Grade grade, String mobile, Role role) {
        this.grade = grade;
        this.mobile = mobile;
        this.role = role;
    }

    public User toEntity(){
        return User.builder()
                .grade(grade)
                .mobile(mobile)
                .role(role).build();
    }


}
