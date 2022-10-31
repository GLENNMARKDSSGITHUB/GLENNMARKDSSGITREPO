package com.dss.dto.user;

import com.dss.dto.roles.RolesDTO;
import com.dss.util.validators.*;

import java.util.Date;
import java.util.List;

public class UsersDTO {

    private String dssUserId;
    @DssNullValidator(message = "First name is mandatory")
    @DssCharNumValidator(message = "Only alphabetical characters allowed.")
    private String firstName;
    @DssNullValidator(message = "Last name is mandatory")
    @DssCharNumValidator(message = "Only alphabetical characters allowed.")
    private String lastName;
    @DssEmailValidator(message = "Please enter a valid email")
    @DssEmailTakenValidator(message = "Email address has already been taken.")
    private String email;
    @DssNullValidator(message = "Password is mandatory")
    @DssPasswordValidator(message = "Password must have at least an uppercase and lowercase alphabet, a digit and a special character")
    private String password;
    private String oldPassword;
    @DssNullValidator(message = "Status is mandatory")
    private String status;
    @DssNullValidator(message = "Cellphone number is mandatory")
    @DssPhoneNoTakenValidator(message = "Cellphone number has already been taken.")
    private String cellphoneNumber;
    private Date creationDate;
    private String createdBy;
    private Date lastModificationDate;
    private String lastModifiedBy;

    private List<RolesDTO> userRoles;

    public UsersDTO() {
    }

    public UsersDTO(String dssUserId, String firstName, String lastName, String email, String password, String oldPassword, String status, String cellphoneNumber, Date creationDate, String createdBy, Date lastModificationDate, String lastModifiedBy, List<RolesDTO> userRoles) {
        this.dssUserId = dssUserId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.oldPassword = oldPassword;
        this.status = status;
        this.cellphoneNumber = cellphoneNumber;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastModificationDate = lastModificationDate;
        this.lastModifiedBy = lastModifiedBy;
        this.userRoles = userRoles;
    }

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

    public List<RolesDTO> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<RolesDTO> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "UsersDTO{" +
                "dssUserId='" + dssUserId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", status='" + status + '\'' +
                ", cellphoneNumber='" + cellphoneNumber + '\'' +
                ", creationDate=" + creationDate +
                ", createdBy='" + createdBy + '\'' +
                ", lastModificationDate=" + lastModificationDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", userRoles=" + userRoles +
                '}';
    }
}
