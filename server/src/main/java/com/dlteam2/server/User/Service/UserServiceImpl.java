package com.dlteam2.server.User.Service;

import com.auth0.jwt.JWT;
import com.dlteam2.server.Exception.ApiException;
import com.dlteam2.server.Exception.ExceptionEnum;
import com.dlteam2.server.User.DTO.ResponseDTO.UserInfoResponseDTO;
import com.dlteam2.server.User.Entity.LoginInfo;
import com.dlteam2.server.User.Entity.LoginInfoId;
import com.dlteam2.server.User.Entity.User;
import com.dlteam2.server.User.Etc.LoginType;
import com.dlteam2.server.User.Repository.LoginInfoRepository;
import com.dlteam2.server.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public UserInfoResponseDTO getUserInfo(String id) {
        Optional<User> user = userRepository.findById(UUID.fromString(id));
        List<LoginInfo> loginInfo = loginInfoRepository.findAllByIdId(UUID.fromString(id));

        if(user.isPresent() && loginInfo.size()>0) {
            List<UserInfoResponseDTO.LoginInfo> loginInfos = new ArrayList<>();
            for(LoginInfo infos : loginInfo){
                loginInfos.add(UserInfoResponseDTO.LoginInfo.builder()
                        .email(infos.getEmail())
                        .loginType(infos.getId().getLoginType().name())
                        .build());
            }

            return UserInfoResponseDTO.builder()
                    .mobile(user.get().getMobile())
                    .loginInfo(loginInfos)
                    .grade(user.get().getGrade().name())
                    .build();
        }else{
            throw new ApiException(ExceptionEnum.REQUEST_VALUE_INVALID);
        }

    }


}
