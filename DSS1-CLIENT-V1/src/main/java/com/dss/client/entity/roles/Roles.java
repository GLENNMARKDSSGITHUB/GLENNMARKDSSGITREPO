/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.client.entity.roles;

import com.dss.client.entity.user.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is an Entity Class for Roles
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Roles {

    private String dssRoleId;
    private String userRole;
    private Users user;

    public Roles(String dssRoleId, String userRole) {
        this.dssRoleId = dssRoleId;
        this.userRole = userRole;
    }
}
