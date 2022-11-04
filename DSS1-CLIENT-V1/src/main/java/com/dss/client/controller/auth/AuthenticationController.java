package com.dss.client.controller.auth;


import com.dss.client.dto.user.UsersDTO;
import com.dss.client.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/API")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login.do")
    public ResponseEntity<String> login(@RequestBody UsersDTO userDto){
        return authenticationService.login(userDto);
    }
}
