package com.dss.client.service.registration;

import com.dss.client.dto.user.UsersDTO;
import com.dss.client.entity.user.Users;

import java.util.List;

public interface RegistrationService {

    String addRegistration(UsersDTO userDto);

    List<Users> displayRegistration();

    List<Users> searchRegistration(String email);
}
