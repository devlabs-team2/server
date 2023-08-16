package com.dlteam2.server.User.Repository;

import com.dlteam2.server.Common.Constants;
import com.dlteam2.server.Common.RepositoryTest;
import com.dlteam2.server.User.Entity.RefreshToken;
import com.dlteam2.server.User.Entity.RefreshTokenId;
import com.dlteam2.server.User.Entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RefreshTokenRepositoryTest extends RepositoryTest {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private UserRepository userRepository;

    @DisplayName("발행일 순으로 정렬하여 가장 최근의 토큰값 하나만 받아오기")
    @Test
    void findTopByTokenOrderyIatDescTest(){
        Calendar cal = Calendar.getInstance();
        User user = User.builder().build();
        user = userRepository.save(user);
        RefreshToken refreshToken1 = RefreshToken.builder()
                        .id(RefreshTokenId.builder().user(user).id(UUID.randomUUID()).build())
                        .token(Constants.test_user_1_token)
                        .iat(new Date(cal.getTimeInMillis()))
                        .exp(false).build();
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -3);
        refreshToken1 = refreshTokenRepository.save(refreshToken1);
        RefreshToken refreshToken2 = RefreshToken.builder()
                .id(RefreshTokenId.builder().user(user).id(UUID.randomUUID()).build())
                .token(Constants.test_user_1_token)
                .iat(new Date(cal2.getTimeInMillis()))
                .exp(false).build();
        refreshToken2 = refreshTokenRepository.save(refreshToken2);
        Optional<RefreshToken> result = refreshTokenRepository.findTopByIdUserIdOrderByIatDesc(user.getId());
        assertThat(result.get().getId().getUser().getId().toString()).isEqualTo(user.getId().toString());
        assertThat(result.get().getIat()).isEqualTo(refreshToken1.getIat());
    }
}
