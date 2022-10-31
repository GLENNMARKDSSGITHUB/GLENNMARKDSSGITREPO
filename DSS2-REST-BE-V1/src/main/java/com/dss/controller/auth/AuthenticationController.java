package com.dss.controller.auth;

import com.dss.controller.actors.ActorsController;
import com.dss.dto.user.UsersDTO;
import com.dss.service.auth.AuthUserDetailsService;
import com.dss.util.utils.DssCommonMessageDetails;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/API")
public class AuthenticationController {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthUserDetailsService authUserDetailsService;

    @GetMapping("/login.do")
    public String login(@RequestBody UsersDTO userDto){
        log.debug("AuthenticationController | login | Start ");
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            commonMsgDtl = authUserDetailsService.login(email, password);
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
