package com.dss.client.service.registration;

import com.dss.client.dto.user.UsersDTO;
import com.dss.client.entity.user.Users;
import com.dss.client.proxy.registration.RegistrationProxy;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RegistrationServiceImpl implements RegistrationService{

    @Autowired
    RegistrationProxy registrationProxy;

    @Override
    public String addRegistration(UsersDTO userDto) {
        return registrationProxy.addRegistration(userDto);
    }

    @Override
    public List<Users> displayRegistration() {
        return registrationProxy.displayRegistration();
    }

    @Override
    public List<Users> searchRegistration(String email) {
        return registrationProxy.searchRegistration(email);
    }
}
