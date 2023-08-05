package com.dlteam2.server.User.Service;

import com.dlteam2.server.Common.ServiceTest;
import com.dlteam2.server.User.Entity.LoginInfo;
import com.dlteam2.server.User.Entity.LoginInfoId;
import com.dlteam2.server.User.Entity.User;
import com.dlteam2.server.User.Repository.LoginInfoRepository;
import com.dlteam2.server.User.Repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest extends ServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private LoginInfoRepository loginInfoRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @DisplayName("전화번호로 id를 조회한다")
    @Test
    void findIdByMobile() {
        User user = User.builder().build();
        when(userRepository.findByMobile("01012345678")).thenReturn(Optional.of(user));
        assertThat(userService.findIdByMobile("01012345678")).isEqualTo(user.getId());
    }

    @DisplayName("id로 이메일을 조회한다")
    @Test
    void findEmail() {
        LoginInfo loginInfo = LoginInfo.builder().build();
        when(loginInfoRepository.findById(any(LoginInfoId.class))).thenReturn(Optional.of(loginInfo));
        assertThat(userService.findEmail("1")).isEqualTo(loginInfo.getEmail());
    }
}