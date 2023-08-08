package com.dlteam2.server.User.Service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String findIdByMobile(String mobile);
    String findEmail(String id);
}
