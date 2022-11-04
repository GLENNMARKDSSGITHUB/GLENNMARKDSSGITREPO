/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.controller.auth;

import com.dss.dto.user.UsersDTO;
import com.dss.service.auth.LoginAuthenticationService;
import com.dss.util.utils.DssCommonMessageDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * This class is a controller layer for DSS Login and Authentication
 */

@RestController
@RequestMapping("/API")
@Slf4j
public class LoginAuthenticationController {
    @Autowired
    private LoginAuthenticationService loginAuthenticationService;

    /** Returns a String value if the admin user successfully login to the DSS web app or not
     * @param userDto userDto
     * @return String
     * @see #login(UsersDTO)
     */
    @PostMapping("/login.do")
    public String login(@RequestBody UsersDTO userDto){
        log.debug("AuthenticationController | login | Start ");
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            String email = userDto.getEmail();
            String password = userDto.getPassword();
            commonMsgDtl = loginAuthenticationService.login(email, password);
            if(commonMsgDtl.isSuccess()){
                log.debug("AuthenticationController | login | getContent : " + commonMsgDtl.getContent());
            }else{
                log.error("AuthenticationController | login | getContent : " + commonMsgDtl.getContent());
            }
        }catch(Exception ex){
            log.error("AuthenticationController | login | Error msg : " + ex.getMessage());
        }finally{
            log.debug("AuthenticationController | login | End ");
        }
        return commonMsgDtl.getContent();
    }
}
