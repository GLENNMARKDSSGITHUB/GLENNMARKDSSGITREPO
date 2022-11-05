/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.entity.user;

import com.dss.entity.roles.Roles;
import java.util.Date;
import java.util.List;

/**
 * This is an Entity Class for Users
 */

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

    public String getDssUserId() {
        return dssUserId;
    }

    public void setDssUserId(String dssUserId) {
        this.dssUserId = dssUserId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
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

    public List<Roles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<Roles> userRoles) {
        this.userRoles = userRoles;
    }
}
