package com.dss.dto;

import java.util.Date;
import java.util.List;

public class RolesDTO {

    private String dssRoleId;
    private String userRole;
    private Date creationDate;
    private String createdBy;
    private Date lastModificationDate;
    private String lastModifiedBy;

    private List<UsersDTO> users;

    public RolesDTO() {
    }

    public RolesDTO(String dssRoleId, String userRole, Date creationDate, String createdBy, Date lastModificationDate, String lastModifiedBy) {
        this.dssRoleId = dssRoleId;
        this.userRole = userRole;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastModificationDate = lastModificationDate;
        this.lastModifiedBy = lastModifiedBy;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public List<UsersDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UsersDTO> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "RolesDTO{" +
                "dssRoleId='" + dssRoleId + '\'' +
                ", userRole='" + userRole + '\'' +
                ", creationDate=" + creationDate +
                ", createdBy='" + createdBy + '\'' +
                ", lastModificationDate=" + lastModificationDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", users=" + users +
                '}';
    }
}
