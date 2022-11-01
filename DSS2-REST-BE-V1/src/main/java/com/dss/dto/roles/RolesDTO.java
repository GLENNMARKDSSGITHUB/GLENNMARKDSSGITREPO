package com.dss.dto.roles;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

public class RolesDTO {

    private String dssRoleId;
    private String userRole;

    public RolesDTO() {
    }

    public RolesDTO(String dssRoleId, String userRole) {
        this.dssRoleId = dssRoleId;
        this.userRole = userRole;
    }

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

    @Override
    public String toString() {
        return "RolesDTO{" +
                "dssRoleId='" + dssRoleId + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
