package com.dss.service.auth;

import com.dss.util.utils.DssCommonMessageDetails;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

public interface AuthenticationService {

    DssCommonMessageDetails login(String email, String password);
}
