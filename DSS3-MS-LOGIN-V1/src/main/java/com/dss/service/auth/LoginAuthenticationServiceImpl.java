/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.service.auth;

import com.dss.entity.user.Users;
import com.dss.repository.user.UsersRepository;
import com.dss.util.exceptions.DssException;
import com.dss.util.utils.CommonStringUtility;
import com.dss.util.utils.DssCommonMessageDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * This class is a service implementation for DSS Login and Authentication
 * @see #login(String , String)
 */

@Service
@Slf4j
public class LoginAuthenticationServiceImpl implements LoginAuthenticationService {
    private final DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private UsersRepository userRepository;

    @Override
    public DssCommonMessageDetails login(String email, String password) {
        log.debug("AuthUserDetailsServiceImpl | login | Start ");
        try{
            Users user = userRepository.findUserByEmailAddress(email);
            if(user != null){
                if(encoder.matches(password, user.getPassword())){
                    commonMsgDtl.setContent("Welcome to Digistream Express!");
                    commonMsgDtl.setSuccess(true);
                }else{
                    commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_001_LOGIN_INCORRECT_PASSWORD);
                    commonMsgDtl.setSuccess(false);
                    log.error("AuthUserDetailsServiceImpl | login | getContent : " + commonMsgDtl.getContent());
                }
            }else{
                commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_001_LOGIN_EMAIL_NOT_CONNECTED);
                commonMsgDtl.setSuccess(false);
                log.error("AuthUserDetailsServiceImpl | login | getContent : " + commonMsgDtl.getContent());
            }
        }catch(Exception ex){
            commonMsgDtl.setSuccess(false);
            log.error("AuthUserDetailsServiceImpl | login | Error : {}", ex.getMessage());
        }finally {
            log.debug("AuthUserDetailsServiceImpl | login | End ");
        }
        return commonMsgDtl;
    }
}
