/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.transformer;

import com.dss.dto.user.UsersDTO;
import com.dss.entity.roles.Roles;
import com.dss.entity.user.Users;
import com.dss.util.enums.UserRoles;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * This class is a DSS2 Account Registration Transformer
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
}
