package com.dss.dto.user;

import com.dss.dto.roles.RolesDTO;
import com.dss.util.annotations.*;
import com.dss.util.utils.CommonStringUtility;
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
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsersDTO {

    private String dssUserId;
    @DssNullValidator(message = CommonStringUtility.ERR_CODE_001_REQ_FIRSTNAME)
    @DssCharNumValidator(message = CommonStringUtility.ERR_CODE_001_ALPHABET_ALLOWED)
    private String firstName;
    @DssNullValidator(message = CommonStringUtility.ERR_CODE_001_REQ_LASTNAME)
    @DssCharNumValidator(message = CommonStringUtility.ERR_CODE_001_ALPHABET_ALLOWED)
    private String lastName;
    @DssEmailValidator(message = CommonStringUtility.ERR_CODE_001_INVALID_EMAIL)
    @DssEmailTakenValidator(message = CommonStringUtility.ERR_CODE_001_EMAIL_TAKEN)
    private String email;
    @DssNullValidator(message = CommonStringUtility.ERR_CODE_001_REQ_PASSWORD )
    @DssPasswordValidator(message = CommonStringUtility.ERR_CODE_001_PASSWORD_ALLOWED)
    private String password;
    private String oldPassword;
    @DssNullValidator(message = CommonStringUtility.ERR_CODE_001_REQ_STATUS)
    private String status;
    @DssNullValidator(message = CommonStringUtility.ERR_CODE_001_REQ_CELLPHONE_NO)
    @DssPhoneNoTakenValidator(message = CommonStringUtility.ERR_CODE_001_CELL_NO_TAKEN)
    private String cellphoneNumber;
    private Date creationDate;
    private String createdBy;
    private Date lastModificationDate;
    private String lastModifiedBy;

    private List<RolesDTO> userRoles;
}
