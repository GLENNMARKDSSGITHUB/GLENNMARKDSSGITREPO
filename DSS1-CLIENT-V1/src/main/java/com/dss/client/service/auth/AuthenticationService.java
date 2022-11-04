package com.dss.client.service.auth;

import com.dss.client.dto.user.UsersDTO;
import org.springframework.http.ResponseEntity;


public interface AuthenticationService {

    ResponseEntity<String> login(UsersDTO userDto);

}
