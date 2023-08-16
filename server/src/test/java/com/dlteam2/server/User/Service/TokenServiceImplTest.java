package com.dlteam2.server.User.Service;

import com.dlteam2.server.Common.Constants;
import com.dlteam2.server.Common.ServiceTest;
import com.dlteam2.server.User.Entity.RefreshToken;
import com.dlteam2.server.User.Entity.RefreshTokenId;
import com.dlteam2.server.User.Entity.User;
import com.dlteam2.server.User.Repository.RefreshTokenRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

public class TokenServiceImplTest extends ServiceTest {
    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @InjectMocks
    private TokenServiceImpl tokenService;

    //TODO - 나머지 로직 개발 후에
//    @DisplayName("access token 재생성")
//    @Test
//    void refreshAccessTokenTest(){
//        String tmpToken = "Bearer tmp";
//        RefreshToken refreshToken = RefreshToken.builder().build();
//        when(refreshTokenRepository.findTopByIdUserIdOrderByIatDesc(UUID.fromString(Constants.test_user_1_id))).thenReturn(Optional.of(refreshToken));
//        when(tokenService.getUserId(tmpToken)).thenReturn(Constants.test_user_1_id);
//        when(tokenService.generateAccessToken(Constants.test_user_1_id)).thenReturn(Constants.test_user_1_token);
//
//        assertThat(tokenService.refreshAccessToken(tmpToken)).isEqualTo(null);
//
//    }
}
