package com.dlteam2.server.User.Controller;

import com.dlteam2.server.Common.ResponseDTO;
import com.dlteam2.server.User.Service.UserService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
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
            String id = userService.findIdByMobile(jsonObject.get("mobile").toString());
            String email = userService.findEmail(jsonObject.get("mobile").toString());

            JSONObject result = new JSONObject();
            result.put("email", email);

            return ResponseDTO.DataResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message(HttpStatus.OK.getReasonPhrase())
                    .data(result).build();

        } catch(Exception e){
            //request 잘못 됐을 때 -> 에러 호출
        }

    }
}
