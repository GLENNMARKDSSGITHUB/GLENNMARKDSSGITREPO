package com.dss.transformer;

import com.dss.dto.user.UsersDTO;
import com.dss.entity.user.Users;

public class RegistrationTransformerTest {

    public Users transformToUsers(UsersDTO userDto){
        return new Users(
                userDto.getDssUserId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getOldPassword(),
                userDto.getStatus(),
                userDto.getCellphoneNumber(),
                userDto.getCreationDate(),
                userDto.getCreatedBy(),
                userDto.getLastModificationDate(),
                userDto.getLastModifiedBy()
        );
    }
}
