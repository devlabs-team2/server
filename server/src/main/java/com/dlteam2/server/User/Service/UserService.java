package com.dlteam2.server.User.Service;

import com.dlteam2.server.User.DTO.ResponseDTO.UserInfoResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String findIdByMobile(String mobile);
    String findEmail(String id);
    String getUserId(String token);
    UserInfoResponseDTO getUserInfo(String id);

    boolean updateMobile(String id, String mobile);
    boolean updateEmail(String id, String email);
    boolean updatePassword(String id, String password);

}
