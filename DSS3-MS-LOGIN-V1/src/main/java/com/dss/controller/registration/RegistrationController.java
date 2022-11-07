/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.controller.registration;

import com.dss.dto.user.UsersDTO;
import com.dss.service.registration.RegistrationService;
import com.dss.util.utils.DssCommonMessageDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This class is a controller layer for DSS Registrations
 */

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/API/registration")
@Slf4j
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    /** Returns a String value if the admin user successfully adds the account registration or not.
     * @param userDto userDto
     * @param result BindingResult
     * @return String
     * @see #addRegistration(UsersDTO, BindingResult)
     */
    @PostMapping("/add-registration.do")
    public String addRegistration(@Valid @RequestBody UsersDTO userDto, BindingResult result){
        log.debug("RegistrationController | addRegistration | Start");
        log.debug("RegistrationController | addRegistration | userDTO: " + userDto.toString());
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            if(result.hasErrors()){
                StringBuilder addStr = new StringBuilder();
                List<ObjectError> errList = result.getAllErrors();
                for(ObjectError err : errList){
                    log.error("RegistrationController | addRegistration | Error msg : " + err.getDefaultMessage());
                    addStr.append(err.getDefaultMessage()).append("\n");
                }
                commonMsgDtl.setContent(addStr.toString());
            }else{
                commonMsgDtl = registrationService.registerAccount(userDto);
                log.debug(commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            log.error("RegistrationController | addRegistration | Error msg {} ", ex.getMessage());
        }finally{
            log.debug("RegistrationController | addRegistration | End ");
        }
        return commonMsgDtl.getContent();
    }
}
