package com.dlteam2.server.User.Entity;

import com.dlteam2.server.User.DTO.UserDTO;
import com.dlteam2.server.User.Etc.Grade;
import com.dlteam2.server.User.Etc.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Table(name="Users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id; //식별 id
    @Enumerated(value = EnumType.STRING)
    private Grade grade; //등급
    private String mobile; //전화번호
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Builder
    public User(Grade grade, String mobile, Role role) {
        this.grade = grade;
        this.mobile = mobile;
        this.role = role;
    }

    public void updateGrade(Grade grade){
        this.grade = grade;
    }

    public void updateMobile(String mobile){
        this.mobile = mobile;
    }
}
