package com.dlteam2.server.User.Repository;

import com.dlteam2.server.Common.Constants;
import com.dlteam2.server.Common.RepositoryTest;
import com.dlteam2.server.User.Entity.User;
import com.dlteam2.server.User.Etc.Grade;
import com.dlteam2.server.User.Etc.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest extends RepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("아이디로 찾기")
    @Test
    void findById() {
        User user = User.builder().grade(Grade.BASIC).mobile(Constants.test_user_1_mobile).role(Role.ROLE_USER).build();
        user = userRepository.save(user);
        assertThat(userRepository.findById(user.getId()).get().getId()).isEqualTo(user.getId());
    }

    @DisplayName("휴대폰 번호로 찾기")
    @Test
    void findByMobile() {
        User user = User.builder().grade(Grade.BASIC).mobile(Constants.test_user_1_mobile).role(Role.ROLE_USER).build();
        user = userRepository.save(user);
        assertThat(userRepository.findByMobile(user.getMobile()).get().getId()).isEqualTo(user.getId());
    }
}