/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */


package com.dss.dto.user;

import com.dss.dto.roles.RolesDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * This class is a Data Transfer Object for UsersDTO
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
