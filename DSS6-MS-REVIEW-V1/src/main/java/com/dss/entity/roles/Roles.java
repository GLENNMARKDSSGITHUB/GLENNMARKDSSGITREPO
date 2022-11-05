/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.entity.roles;

import com.dss.entity.user.Users;

/**
 * This is an Entity Class for Roles
 */

public class Roles {

    private String dssRoleId;
    private String userRole;

    private Users user;

    public String getDssRoleId() {
        return dssRoleId;
    }

    public void setDssRoleId(String dssRoleId) {
        this.dssRoleId = dssRoleId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
