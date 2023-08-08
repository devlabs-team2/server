package com.dlteam2.server.User.Repository;

import com.dlteam2.server.Common.Constants;
import com.dlteam2.server.Common.RepositoryTest;
import com.dlteam2.server.User.Entity.LoginInfo;
import com.dlteam2.server.User.Entity.LoginInfoId;
import com.dlteam2.server.User.Entity.User;
import com.dlteam2.server.User.Etc.Grade;
import com.dlteam2.server.User.Etc.LoginType;
import com.dlteam2.server.User.Etc.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class LoginInfoRepositoryTest extends RepositoryTest {

    @Autowired
    private LoginInfoRepository loginInfoRepository;
    @Autowired
    private UserRepository userRepository;

    @DisplayName("아이디로 찾기")
    @Test
    void findById() {
        User user = User.builder().grade(Grade.BASIC).mobile(Constants.test_user_1_mobile).role(Role.ROLE_USER).build();
        user = userRepository.save(user);
        LoginInfoId loginInfoId = LoginInfoId.builder().id(user.getId()).loginType(LoginType.BASIC).build();
        LoginInfo loginInfo = LoginInfo.builder().id(loginInfoId).user(user).build();
        loginInfoRepository.save(loginInfo);
        assertThat(loginInfoRepository.findById(loginInfoId).get().getId().getId().toString()).isEqualTo(user.getId().toString());

    }
}