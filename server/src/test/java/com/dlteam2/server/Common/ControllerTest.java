package com.dlteam2.server.Common;

import com.dlteam2.server.User.Controller.TokenController;
import com.dlteam2.server.User.Controller.UserController;
import com.dlteam2.server.User.Service.TokenServiceImpl;
import com.dlteam2.server.User.Service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({
        UserController.class,
        TokenController.class
})
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public abstract class ControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected UserServiceImpl userService;
    @MockBean
    protected TokenServiceImpl tokenService;

}
