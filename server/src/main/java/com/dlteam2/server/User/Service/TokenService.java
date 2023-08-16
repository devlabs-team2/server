package com.dlteam2.server.User.Service;

import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    String getUserId(String token);
    String generateAccessToken(String id);
    String refreshAccessToken(String token);
}
