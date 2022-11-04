package com.dss.client.service.auth;

import com.dss.client.proxy.auth.RegistrationAndAuthProxy;
import com.dss.client.dto.user.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    RegistrationAndAuthProxy authenticationStoreClient;

    @Override
    public ResponseEntity<String> login(UsersDTO userDto) {
        return authenticationStoreClient.login(userDto);
    }

}
