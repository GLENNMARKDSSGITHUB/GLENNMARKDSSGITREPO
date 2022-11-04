package com.dss.client.proxy.auth;

import com.dss.client.dto.user.UsersDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "DSS3-MS-LOGIN-V1" , url ="http://localhost:9005")
public interface RegistrationAndAuthProxy {
    @PostMapping("/API/login.do")
    ResponseEntity<String> login(@RequestBody UsersDTO userDto);

}
