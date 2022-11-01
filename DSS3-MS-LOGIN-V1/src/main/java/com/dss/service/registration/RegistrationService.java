package com.dss.service.registration;

import com.dss.dto.user.UsersDTO;
import com.dss.util.utils.DssCommonMessageDetails;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

public interface RegistrationService {

    DssCommonMessageDetails registerAccount(UsersDTO user);
    DssCommonMessageDetails displayRegistrations();
    DssCommonMessageDetails searchRegistrationByEmail(String email);
    DssCommonMessageDetails changePassword(String email, String newPassword, String confirmPassword);
    DssCommonMessageDetails deleteAccount(String email, String password);
}
