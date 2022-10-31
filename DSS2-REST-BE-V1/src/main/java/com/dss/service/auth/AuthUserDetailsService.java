package com.dss.service.auth;

import com.dss.util.utils.DssCommonMessageDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthUserDetailsService extends UserDetailsService {

    DssCommonMessageDetails login(String email, String password);
}
