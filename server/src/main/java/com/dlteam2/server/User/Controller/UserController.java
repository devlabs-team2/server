package com.dlteam2.server.User.Controller;

import com.dlteam2.server.Common.ResponseDTO;
import com.dlteam2.server.Exception.ApiException;
import com.dlteam2.server.Exception.ExceptionEnum;
import com.dlteam2.server.User.DTO.ResponseDTO.UserInfoResponseDTO;
import com.dlteam2.server.User.Service.UserService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/search/email")
    public ResponseDTO.DataResponse searchEmail(@RequestBody JSONObject jsonObject){
        try {
            String mobile = jsonObject.get("mobile").toString();
            String id = userService.findIdByMobile(mobile);
            String email = userService.findEmail(id);

            JSONObject result = new JSONObject();
            result.put("email", email);

            return ResponseDTO.DataResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message(HttpStatus.OK.getReasonPhrase())
                    .data(result).build();

        } catch(Exception e){
            //request 잘못 됐을 때 -> 에러 호출
            throw new ApiException(ExceptionEnum.REQUEST_VALUE_INVALID);
        }

    }

    @GetMapping ("/info")
    public ResponseDTO.ObjectDataResponse userInfo(@RequestHeader("Authorization") String data){
        String id = userService.getUserId(data);
        UserInfoResponseDTO resultData = userService.getUserInfo(id);

        JSONObject result = new JSONObject();
        result.put("data", resultData);

        return ResponseDTO.ObjectDataResponse.builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(resultData).build();
    }
}
