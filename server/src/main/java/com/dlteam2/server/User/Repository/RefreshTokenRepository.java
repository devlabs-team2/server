package com.dlteam2.server.User.Repository;

import com.dlteam2.server.User.Entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findTopByIdUserIdOrderByIatDesc(UUID userId);
}
