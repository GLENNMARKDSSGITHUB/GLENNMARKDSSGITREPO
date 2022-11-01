package com.dss.controller.auth;

import com.dss.dto.user.UsersDTO;
import com.dss.service.auth.LoginAuthenticationService;
import com.dss.util.utils.DssCommonMessageDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

@RestController
@RequestMapping("/API")
@Slf4j
public class LoginAuthenticationController {
    @Autowired
    private LoginAuthenticationService loginAuthenticationService;

    @GetMapping("/login.do")
    public String login(@RequestBody UsersDTO userDto){
        log.debug("AuthenticationController | login | Start ");
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
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
