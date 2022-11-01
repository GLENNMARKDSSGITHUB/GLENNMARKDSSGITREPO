package com.dss.transformer.registration;

import com.dss.dto.user.UsersDTO;
import com.dss.entity.roles.Roles;
import com.dss.entity.user.Users;
import com.dss.util.enums.UserRoles;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

public class RegistrationTransformer {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public List<Roles> populateRolesRegistration(UsersDTO userDto, Users user){
        return Collections.singletonList(new Roles(
                UserRoles.ROLE_SUPER_ADMIN.getStrRoleId(),
                userDto.getUserRoles().get(0).getUserRole(),
                user
        ));
    }

    public Users populateUsersRegistration(UsersDTO userDto){
        return new Users(
                userDto.getDssUserId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                encoder.encode(userDto.getPassword()),
                null,
                userDto.getStatus(),
                userDto.getCellphoneNumber(),
                new Date(),
                UserRoles.ROLE_ADMIN.getStrRole(),
                userDto.getLastModificationDate(),
                userDto.getLastModifiedBy()
        );
    }

    public Users populateUsersRegistration(Users user){
        return new Users(
                user.getDssUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                encoder.encode(user.getPassword()),
                encoder.encode(user.getOldPassword()),
                user.getStatus(),
                user.getCellphoneNumber(),
                user.getCreationDate(),
                user.getCreatedBy(),
                user.getLastModificationDate(),
                user.getLastModifiedBy()
        );
    }

    public List<Users> populateUsersRegistration(List<Users> userList){
        List<Users> users = new ArrayList<>();

        for (Users user : userList) {
            List<Roles> rolesList = new ArrayList<>();
            for(Roles r : user.getUserRoles()){
                rolesList.add(new Roles(
                        r.getDssRoleId(),
                        r.getUserRole()
                ));
            }

            users.add(new Users(
                    user.getDssUserId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getOldPassword(),
                    user.getStatus(),
                    user.getCellphoneNumber(),
                    user.getCreationDate(),
                    user.getCreatedBy(),
                    user.getLastModificationDate(),
                    user.getLastModifiedBy(),
                    rolesList
            ));
        }
        return users;
    }
}
