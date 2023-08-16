package com.dlteam2.server.User.Service;

import com.auth0.jwt.JWT;
import com.dlteam2.server.Exception.ApiException;
import com.dlteam2.server.Exception.ExceptionEnum;
import com.dlteam2.server.User.Entity.RefreshToken;
import com.dlteam2.server.User.Repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService{
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public TokenServiceImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public String getUserId(String token) {
        try {
            token = token.replaceFirst("Bearer ","");
            String id = JWT.decode(token)
                    .getClaim("id")
                    .asString();
            return id;
        } catch(Exception e){
            throw new ApiException(ExceptionEnum.INVALID_TOKEN);
        }
    }

    //TODO - access token 생성
    @Override
    public String generateAccessToken(String id) {
        return null;
    }

    @Override
    public String refreshAccessToken(String token) {
        String userId = getUserId(token);
        Optional<RefreshToken> latestRefreshToken = refreshTokenRepository.findTopByIdUserIdOrderByIatDesc(UUID.fromString(userId));
        if(latestRefreshToken.isPresent() && latestRefreshToken.get().getId().getUser().getId().equals(UUID.fromString(userId))){
            if(!latestRefreshToken.get().isExp()){
                String refreshAccessToken = generateAccessToken(userId);
                return refreshAccessToken;
            }else{
                throw new ApiException(ExceptionEnum.EXPIRED_TOKEN);
            }
        }else{
            throw new ApiException(ExceptionEnum.REQUEST_VALUE_INVALID);
        }
    }

}
