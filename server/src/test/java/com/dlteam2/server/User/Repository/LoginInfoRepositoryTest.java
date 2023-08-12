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

import java.util.Optional;
import java.util.UUID;

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

    @DisplayName("아이디의 아이디(복합키)로 찾기")
    @Test
    void findByIdId(){
        User user = User.builder().grade(Grade.BASIC).mobile(Constants.test_user_1_mobile).role(Role.ROLE_USER).build();
        user = userRepository.save(user);
        LoginInfoId loginInfoId = LoginInfoId.builder().id(user.getId()).loginType(LoginType.BASIC).build();
        LoginInfo loginInfo = LoginInfo.builder().id(loginInfoId).user(user).email(Constants.test_user_1_email).build();
        loginInfoRepository.save(loginInfo);
        assertThat(loginInfoRepository.findAllByIdId(user.getId()).get(0).getId().getId().toString()).isEqualTo(user.getId().toString());
        assertThat(loginInfoRepository.findAllByIdId(user.getId()).get(0).getEmail()).isEqualTo(loginInfo.getEmail());

    }

    @DisplayName("저장/업데이트하기")
    @Test
    void updateLoginInfo(){
        String tmp_email = "000-0000-0000";

        User user = User.builder().grade(Grade.BASIC).mobile(Constants.test_user_1_mobile).role(Role.ROLE_USER).build();
        user = userRepository.save(user);

        LoginInfoId loginInfoId = LoginInfoId.builder().id(user.getId()).loginType(LoginType.BASIC).build();
        LoginInfo loginInfo = LoginInfo.builder().id(loginInfoId).user(user).email(tmp_email).build();
        loginInfo = loginInfoRepository.save(loginInfo); //최초 저장

        assertThat(loginInfo.getEmail()).isEqualTo(tmp_email);

        loginInfo.updateEmail(Constants.test_user_1_email);
        LoginInfo loginInfo2 = loginInfoRepository.save(loginInfo); //업데이트
        assertThat(loginInfo2.getId().getId()).isEqualTo(loginInfo.getId().getId());
        assertThat(loginInfo2.getId().getLoginType()).isEqualTo(loginInfo.getId().getLoginType());

        Optional<LoginInfo> result = loginInfoRepository.findById(loginInfo.getId()); //찾기
        assertThat(result.get().getId().getId()).isEqualTo(loginInfo.getId().getId());
        assertThat(result.get().getId().getLoginType()).isEqualTo(loginInfo.getId().getLoginType());
        assertThat(result.get().getEmail()).isEqualTo(loginInfo.getEmail());

    }
}