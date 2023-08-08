package com.dlteam2.server.Common.Builder;

import com.dlteam2.server.User.Repository.LoginInfoRepository;
import com.dlteam2.server.User.Repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class BuilderSupporter {
    private final UserRepository userRepository;
    private final LoginInfoRepository loginInfoRepository;

    public BuilderSupporter(UserRepository userRepository, LoginInfoRepository loginInfoRepository) {
        this.userRepository = userRepository;
        this.loginInfoRepository = loginInfoRepository;
    }

    public UserRepository userRepository(){
        return userRepository;
    }

    public LoginInfoRepository loginInfoRepository(){
        return loginInfoRepository;
    }
}
