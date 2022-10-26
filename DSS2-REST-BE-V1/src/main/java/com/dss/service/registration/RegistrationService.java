package com.dss.service.registration;

import com.dss.dto.UsersDTO;
import com.dss.util.common.DigiStreamCommonMessageDetails;

public interface RegistrationService {

    DigiStreamCommonMessageDetails addRegistration(UsersDTO user);
    DigiStreamCommonMessageDetails displayRegistrations();
    DigiStreamCommonMessageDetails searchRegistrationByEmail(String email);
    DigiStreamCommonMessageDetails updateRegistration(UsersDTO user);
    DigiStreamCommonMessageDetails deleteRegistration(String email, String password);
}
