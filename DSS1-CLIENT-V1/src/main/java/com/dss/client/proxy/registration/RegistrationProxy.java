package com.dss.client.proxy.registration;

import com.dss.client.configuration.CustomFeignClientConfiguration;
import com.dss.client.dto.user.UsersDTO;
import com.dss.client.entity.user.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "${feign.login-register-name}" , url ="${feign.login-register-url}", configuration = CustomFeignClientConfiguration.class)
public interface RegistrationProxy {

    @PostMapping("/API/registration/add-registration.do")
    String addRegistration(UsersDTO userDto);

    @GetMapping("/API/registration/display-registrations.do")
    List<Users> displayRegistration();

    @GetMapping("/API/registration/search-registration.do/{email}")
    List<Users> searchRegistration(@PathVariable("email") String email);
}
