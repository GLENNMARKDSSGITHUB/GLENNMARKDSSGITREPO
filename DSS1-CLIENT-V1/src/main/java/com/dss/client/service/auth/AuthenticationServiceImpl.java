package com.dss.client.service.auth;

import com.dss.client.proxy.auth.AuthenticationProxy;
import com.dss.client.dto.user.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    AuthenticationProxy authenticationStoreClient;

    @Override
    public ResponseEntity<String> login(UsersDTO userDto) {
        return authenticationStoreClient.login(userDto);
    }

}
