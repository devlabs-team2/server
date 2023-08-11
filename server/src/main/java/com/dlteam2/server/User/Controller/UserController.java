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
    public ResponseDTO.DataResponse searchEmail(@RequestBody JSONObject data){
        try {
            String mobile = data.get("mobile").toString();
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
    public ResponseDTO.ObjectDataResponse userInfo(@RequestHeader("Authorization") String token){
        String id = userService.getUserId(token);
        UserInfoResponseDTO resultData = userService.getUserInfo(id);

        JSONObject result = new JSONObject();
        result.put("data", resultData);

        return ResponseDTO.ObjectDataResponse.builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(resultData).build();
    }

    @PatchMapping("/info/update/mobile")
    public ResponseDTO.DataResponse updateMobile(@RequestHeader("Authorization") String token, @RequestBody JSONObject data){
        String id = userService.getUserId(token);
        String mobile = data.get("mobile").toString();
        JSONObject result = new JSONObject();
        if(userService.updateMobile(id, mobile)){
            result.put("result","SUCCESS");
        }else{
            result.put("result","FAIL");
        }
        return ResponseDTO.DataResponse.builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(result).build();
    }
}
