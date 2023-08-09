package com.dlteam2.server.User.Repository;

import com.dlteam2.server.User.Entity.LoginInfo;
import com.dlteam2.server.User.Entity.LoginInfoId;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoginInfoRepository extends JpaRepository<LoginInfo, String> {
    Optional<LoginInfo> findById(LoginInfoId loginInfoId);
    List<LoginInfo> findAllByIdId(UUID id);
}
