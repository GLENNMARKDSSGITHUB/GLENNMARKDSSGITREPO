package com.dss.client.controller.registration;


import com.dss.client.dto.user.UsersDTO;
import com.dss.client.entity.user.Users;
import com.dss.client.service.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/add-registration.do")
    String addRegistration(@RequestBody UsersDTO userDto){
        return registrationService.addRegistration(userDto);
    }

    @GetMapping("/display-registrations.do")
    public List<Users> displayRegistration(){
        return registrationService.displayRegistration();
    }

    @GetMapping("/search-registration.do/{email}")
    public List<Users> searchRegistration(@PathVariable("email") String email){
        return registrationService.searchRegistration(email);
    }
}
