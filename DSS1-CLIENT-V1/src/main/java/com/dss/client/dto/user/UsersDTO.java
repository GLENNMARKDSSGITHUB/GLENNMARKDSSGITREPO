package com.dss.client.dto.user;

import com.dss.client.dto.roles.RolesDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsersDTO {

    private String dssUserId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String oldPassword;
    private String status;
    private String cellphoneNumber;
    private Date creationDate;
    private String createdBy;
    private Date lastModificationDate;
    private String lastModifiedBy;

    private List<RolesDTO> userRoles;
}
