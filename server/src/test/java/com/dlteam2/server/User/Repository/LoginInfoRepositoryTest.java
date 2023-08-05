package com.dlteam2.server.User.Repository;

import com.dlteam2.server.User.Entity.LoginInfo;
import com.dlteam2.server.User.Entity.LoginInfoId;
import com.dlteam2.server.User.Entity.User;
import com.dlteam2.server.User.Etc.Grade;
import com.dlteam2.server.User.Etc.LoginType;
import com.dlteam2.server.User.Etc.Role;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class LoginInfoRepositoryTest {

    @Autowired
    private LoginInfoRepository loginInfoRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void findById() {
        User user = User.builder().grade(Grade.BASIC).mobile("01012345678").role(Role.ROLE_USER).build();
        user = userRepository.save(user);
        LoginInfoId loginInfoId = LoginInfoId.builder().id(user.getId()).loginType(LoginType.BASIC).build();
        LoginInfo loginInfo = LoginInfo.builder().id(loginInfoId).user(user).build();
        loginInfoRepository.save(loginInfo);
        assertThat(loginInfoRepository.findById(loginInfoId).get().getId().getId().toString()).isEqualTo(user.getId().toString());

    }
}