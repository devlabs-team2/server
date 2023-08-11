package com.dlteam2.server.User.Repository;

import com.dlteam2.server.Common.Constants;
import com.dlteam2.server.Common.RepositoryTest;
import com.dlteam2.server.User.Entity.User;
import com.dlteam2.server.User.Etc.Grade;
import com.dlteam2.server.User.Etc.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

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

    @DisplayName("저장/업데이트하기")
    @Test
    void updateUser(){
        String tmp_mobile = "000-0000-0000";
        User user = User.builder().grade(Grade.BASIC).mobile(tmp_mobile).role(Role.ROLE_USER).build();
        user = userRepository.save(user); //최초 저장
        assertThat(user.getMobile()).isEqualTo(tmp_mobile);

        user.updateMobile(Constants.test_user_1_mobile);
        User user2 = userRepository.save(user); //업데이트
        assertThat(user2.getId()).isEqualTo(user.getId());

        Optional<User> result = userRepository.findById(user.getId()); //찾기
        assertThat(result.get().getId()).isEqualTo(user.getId());
        assertThat(result.get().getMobile()).isEqualTo(Constants.test_user_1_mobile);
    }
}