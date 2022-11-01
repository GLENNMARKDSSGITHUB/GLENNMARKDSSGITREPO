package com.dss.service.auth;

import com.dss.entity.user.Users;
import com.dss.repository.user.UsersRepository;
import com.dss.util.utils.DssCommonMessageDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
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
                    commonMsgDtl.setSuccess(false);
                    log.error("AuthUserDetailsServiceImpl | login | getContent : " + commonMsgDtl.getContent());
                }else{
                    commonMsgDtl.setContent("The password you’ve entered is incorrect.");
                    commonMsgDtl.setSuccess(false);
                    log.error("AuthUserDetailsServiceImpl | login | getContent : " + commonMsgDtl.getContent());
                }
            }else{
                commonMsgDtl.setContent("The email you entered isn’t connected to an account.");
                commonMsgDtl.setSuccess(false);
                log.error("AuthUserDetailsServiceImpl | login | getContent : " + commonMsgDtl.getContent());
            }
        }catch(Exception ex){
            log.error("AuthUserDetailsServiceImpl | login | Error msg : " + ex.getMessage());
        }finally {
            log.debug("AuthUserDetailsServiceImpl | login | End ");
        }
        return commonMsgDtl;
    }
}
