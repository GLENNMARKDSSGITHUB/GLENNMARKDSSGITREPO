package com.dss.client.controller.auth;


import com.dss.client.dto.user.UsersDTO;
import com.dss.client.entity.user.Users;
import com.dss.client.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/API")
public class RegistrationAndAuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login.do")
    ResponseEntity<String> login(@RequestBody UsersDTO userDto){
        return authenticationService.login(userDto);
    }

}
