package com.dlteam2.server.User.Controller;

import com.dlteam2.server.Common.ResponseDTO;
import com.dlteam2.server.Exception.ApiException;
import com.dlteam2.server.Exception.ExceptionEnum;
import com.dlteam2.server.User.Service.TokenService;
import com.dlteam2.server.User.Service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
public class TokenController {
    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    public TokenController(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping("refresh")
    public ResponseDTO.DataResponse accessTokenRefresh(@RequestBody JSONObject data){
        try{
            String refreshToken = data.get("refresh_token").toString();
            String accessToken = tokenService.refreshAccessToken(refreshToken);
            JSONObject result = new JSONObject();
            result.put("access_token", accessToken);
            return ResponseDTO.DataResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message(HttpStatus.OK.getReasonPhrase())
                    .data(result).build();
        }catch(Exception e){
            throw new ApiException(ExceptionEnum.REQUEST_VALUE_INVALID);
        }
    }
}
