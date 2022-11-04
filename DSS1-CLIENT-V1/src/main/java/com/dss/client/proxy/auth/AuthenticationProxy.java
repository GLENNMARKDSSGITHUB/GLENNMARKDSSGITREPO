package com.dss.client.proxy.auth;

import com.dss.client.dto.user.UsersDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "${feign.login-register-name}" , url ="${feign.login-register-url}")
public interface AuthenticationProxy {

    @PostMapping("/API/login.do")
    ResponseEntity<String> login(UsersDTO userDto);
}
