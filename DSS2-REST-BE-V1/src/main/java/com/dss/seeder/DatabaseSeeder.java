package com.dss.seeder;

import com.dss.entity.Roles;
import com.dss.entity.Users;
import com.dss.repository.RegistrationRepository;
import com.dss.repository.RolesRepository;
import com.dss.util.enums.UserRoles;
import com.dss.util.enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DatabaseSeeder {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private RolesRepository rolesRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUser();
    }

    private void seedUser(){
        List<Users> users = registrationRepository.findAll();
        if(users.isEmpty()){
            List<Roles> role = Collections.singletonList(new Roles(
                            "DSS0001",
                            UserRoles.ROLE_ADMIN.toString(),
                            new Date(),
                            UserRoles.ROLE_ADMIN.toString(),
                            null,
                            null));

            Users user = new Users(
                    "DSS0001",
                    "Glenn Mark",
                    "Anduiza",
                    "glenmark.ghl@gmail.com",
                    encoder.encode("password"),
                    UserStatus.ACTIVE.toString(),
                    "09106121529",
                    new Date(),
                    UserRoles.ROLE_ADMIN.toString(),
                    null,
                    null,
                    role
            );

            rolesRepository.save(role.get(0));
            registrationRepository.save(user);
        }
    }

}
