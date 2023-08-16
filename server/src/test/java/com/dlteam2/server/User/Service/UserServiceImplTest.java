package com.dlteam2.server.User.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dlteam2.server.Common.Constants;
import com.dlteam2.server.Common.ServiceTest;
import com.dlteam2.server.User.DTO.ResponseDTO.UserInfoResponseDTO;
import com.dlteam2.server.User.Entity.LoginInfo;
import com.dlteam2.server.User.Entity.LoginInfoId;
import com.dlteam2.server.User.Entity.User;
import com.dlteam2.server.User.Etc.Grade;
import com.dlteam2.server.User.Etc.LoginType;
import com.dlteam2.server.User.Repository.LoginInfoRepository;
import com.dlteam2.server.User.Repository.UserRepository;
import org.apache.tomcat.util.bcel.Const;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest extends ServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private LoginInfoRepository loginInfoRepository;

    @InjectMocks
    private UserServiceImpl userService;
    @InjectMocks
    private TokenServiceImpl tokenService;

    @DisplayName("전화번호로 id를 조회한다")
    @Test
    void findIdByMobile() {
        User user = User.builder().build();
        when(userRepository.findByMobile(Constants.test_user_1_mobile)).thenReturn(Optional.of(user));
        assertThat(userService.findIdByMobile(Constants.test_user_1_mobile)).isEqualTo(user.getId());
    }

    @DisplayName("id로 이메일을 조회한다")
    @Test
    void findEmail() {
        LoginInfo loginInfo = LoginInfo.builder().build();
        when(loginInfoRepository.findById(any(LoginInfoId.class))).thenReturn(Optional.of(loginInfo));
        assertThat(userService.findEmail(Constants.test_user_1_id)).isEqualTo(loginInfo.getEmail());
    }

    @DisplayName("토큰으로 id를 조회한다")
    @Test
    void getUserId(){
        String jwt = JWT.create()
                .withSubject(Constants.test_user_1_id)
                .withExpiresAt(new Date(System.currentTimeMillis()))
                .withClaim("id", Constants.test_user_1_id)
                .sign(Algorithm.HMAC512("secret"));
        assertThat(tokenService.getUserId("Bearer " + jwt)).isEqualTo(Constants.test_user_1_id);
    }

    @DisplayName("id로 회원 정보를 반환한다")
    @Test
    void getUserInfo(){
        User user = User.builder().grade(Grade.BASIC).mobile(Constants.test_user_1_mobile).role(Constants.test_user_1_role).build();
        LoginInfo loginInfo = LoginInfo.builder()
                        .id(LoginInfoId.builder()
                                .id(UUID.fromString(Constants.test_user_1_id))
                                .loginType(LoginType.BASIC)
                                .build())
                        .email(Constants.test_user_1_email)
                        .build();
        when(userRepository.findById(UUID.fromString(Constants.test_user_1_id))).thenReturn(Optional.of(user));
        when(loginInfoRepository.findAllByIdId(UUID.fromString(Constants.test_user_1_id))).thenReturn(List.of(loginInfo));
        assertThat(userService.getUserInfo(Constants.test_user_1_id).getMobile()).isEqualTo(Constants.test_user_1_mobile);
    }

    @DisplayName("휴대폰 번호를 변경한다")
    @Test
    void updateMobile(){
        User user = User.builder().grade(Grade.BASIC).mobile(Constants.test_user_1_mobile).role(Constants.test_user_1_role).build();
        when(userRepository.findById(UUID.fromString(Constants.test_user_1_id))).thenReturn(Optional.of(user));
        boolean result = userService.updateMobile(Constants.test_user_1_id, Constants.test_user_1_mobile);

        assertTrue(result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @DisplayName("이메일을 변경한다")
    @Test
    void updateEmail(){
        LoginInfo loginInfo = LoginInfo.builder()
                .id(LoginInfoId.builder()
                        .id(UUID.fromString(Constants.test_user_1_id))
                        .loginType(LoginType.BASIC)
                        .build())
                .email(Constants.test_user_1_email)
                .build();
        when(loginInfoRepository.findById(any(LoginInfoId.class))).thenReturn(Optional.of(loginInfo));
        boolean result = userService.updateEmail(Constants.test_user_1_id, Constants.test_user_1_email);

        assertTrue(result);
        verify(loginInfoRepository, times(1)).save(any(LoginInfo.class));
    }

    @DisplayName("비밀번호를 변경하다")
    @Test
    void updatePassword(){
        LoginInfo loginInfo = LoginInfo.builder()
                .id(LoginInfoId.builder()
                        .id(UUID.fromString(Constants.test_user_1_id))
                        .loginType(LoginType.BASIC)
                        .build())
                .email(Constants.test_user_1_email)
                .password(Constants.test_user_1_password)
                .build();
        when(loginInfoRepository.findById(any(LoginInfoId.class))).thenReturn(Optional.of(loginInfo));
        boolean result = userService.updatePassword(Constants.test_user_1_id, Constants.test_user_1_password);

        assertTrue(result);
        verify(loginInfoRepository, times(1)).save(any(LoginInfo.class));
    }
}