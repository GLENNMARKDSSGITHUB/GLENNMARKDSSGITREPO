package com.dss.util.enums;

public enum UserRoles {
    ROLE_SUPER_ADMIN("RS1", "ROLE_SUPER_ADMIN"),
    ROLE_ADMIN("RS2", "ROLE_ADMIN"),
    ROLE_USER("RS3", "ROLE_USER");

    private final String strRoleId;
    private final String strRole;

    UserRoles(String roleId, String role) {
        this.strRoleId = roleId;
        this.strRole = role;
    }

    public String getStrRoleId() {
        return strRoleId;
    }

    public String getStrRole() {
        return strRole;
    }
}
