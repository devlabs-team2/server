package com.dlteam2.server.User.Service;

import com.dlteam2.server.Exception.ApiException;
import com.dlteam2.server.Exception.ExceptionEnum;
import com.dlteam2.server.User.Entity.LoginInfo;
import com.dlteam2.server.User.Entity.LoginInfoId;
import com.dlteam2.server.User.Entity.User;
import com.dlteam2.server.User.Etc.LoginType;
import com.dlteam2.server.User.Repository.LoginInfoRepository;
import com.dlteam2.server.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final LoginInfoRepository loginInfoRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, LoginInfoRepository loginInfoRepository){
        this.userRepository = userRepository;
        this.loginInfoRepository = loginInfoRepository;
    }

    @Override
    public String findIdByMobile(String mobile) {
        Optional<User> result = userRepository.findByMobile(mobile);
        if (result.isPresent()){
            return result.get().getId().toString();
        }else{
            throw new ApiException(ExceptionEnum.REQUEST_VALUE_INVALID);
        }
    }

    @Override
    public String findEmail(String id){
        LoginInfoId loginInfoId = LoginInfoId.builder()
                .id(UUID.fromString(id))
                .loginType(LoginType.BASIC).build();
        Optional<LoginInfo> result = loginInfoRepository.findById(loginInfoId);
        if (result.isPresent()){
            return result.get().getEmail();
        }else{
            throw new ApiException(ExceptionEnum.REQUEST_VALUE_INVALID);
        }
    }


}
