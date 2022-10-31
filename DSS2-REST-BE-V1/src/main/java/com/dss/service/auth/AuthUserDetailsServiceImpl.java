package com.dss.service.auth;

import com.dss.entity.user.Users;
import com.dss.repository.user.UsersRepository;
import com.dss.util.utils.CommonStringUtility;
import com.dss.util.utils.DssCommonMessageDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsServiceImpl implements AuthUserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(AuthUserDetailsServiceImpl.class);
    private final DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findUserByEmailAddress(username);
        if(user == null){
            throw new UsernameNotFoundException(CommonStringUtility.ERR_CODE_004_ACCT_NOT_EXISTING);
        }
        return new AuthUserDetailsImpl(user);
    }

    @Override
    public DssCommonMessageDetails login(String email, String password) {
        logger.debug("AuthUserDetailsServiceImpl | login | Start ");
        try{
            Users user = userRepository.findUserByEmailAddress(email);
            if(user != null){
                if(encoder.matches(password, user.getPassword())){
                    commonMsgDtl.setContent("Welcome to Digistream Express!");
                    commonMsgDtl.setSuccess(false);
                    logger.error("AuthUserDetailsServiceImpl | login | getContent : " + commonMsgDtl.getContent());
                }else{
                    commonMsgDtl.setContent("The password you’ve entered is incorrect.");
                    commonMsgDtl.setSuccess(false);
                    logger.error("AuthUserDetailsServiceImpl | login | getContent : " + commonMsgDtl.getContent());
                }
            }else{
                commonMsgDtl.setContent("The email you entered isn’t connected to an account.");
                commonMsgDtl.setSuccess(false);
                logger.error("AuthUserDetailsServiceImpl | login | getContent : " + commonMsgDtl.getContent());
            }
        }catch(Exception ex){
            logger.error("AuthUserDetailsServiceImpl | login | Error msg : " + ex.getMessage());
        }finally {
            logger.debug("AuthUserDetailsServiceImpl | login | End ");
        }
        return commonMsgDtl;
    }
}
