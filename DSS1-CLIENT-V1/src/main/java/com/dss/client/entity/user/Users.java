/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.client.entity.user;

import com.dss.client.entity.roles.Roles;
import lombok.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Users {

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

    private List<Roles> userRoles;
}
