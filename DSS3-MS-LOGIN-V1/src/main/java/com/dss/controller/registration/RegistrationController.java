package com.dss.controller.registration;

import com.dss.dto.user.UsersDTO;
import com.dss.entity.user.Users;
import com.dss.service.registration.RegistrationService;
import com.dss.util.utils.DssCommonMessageDetails;
import com.dss.util.utils.DssCommonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/API/registration")
@Slf4j
public class RegistrationController {
    private final DssCommonUtility commonUtil = new DssCommonUtility();

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/add-registration.do")
    public ResponseEntity<String> addRegistration(@Valid @RequestBody UsersDTO userDto, BindingResult result){
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
            log.error("RegistrationController | addRegistration | Error msg : " + ex.getMessage());
        }finally{
            log.debug("RegistrationController | addRegistration | End ");
        }
        return new ResponseEntity<>(commonMsgDtl.getContent(), HttpStatus.OK);
    }

    @GetMapping("/display-registrations.do")
    public ResponseEntity<List<Users>> displayRegistration(){
        log.debug("RegistrationController | displayRegistration | Start");
        List<Users> userList = null;
        try{
            DssCommonMessageDetails commonMsgDtl = registrationService.displayRegistrations();
            if(commonMsgDtl.isSuccess()){
                userList = (List<Users>)commonMsgDtl.getObjList();
                commonMsgDtl.setContent(commonUtil.gsonToJsonString(userList));
                log.debug("RegistrationController | displayRegistration | registrations : \n" + commonUtil.gsonToJsonString(userList));
            }else{
                log.error("RegistrationController | displayRegistration | registrations : " + commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            log.error("RegistrationController | displayRegistration | Error msg : " + ex.getMessage());
        }finally{
            log.debug("RegistrationController | displayRegistration | End ");
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/search-registration.do")
    public ResponseEntity<List<Users>> searchRegistration(HttpServletRequest request){
        log.debug("RegistrationController | searchRegistration | Start");
        List<Users> userList = null;
        try{
            String email = request.getParameter("email");
            DssCommonMessageDetails commonMsgDtl = registrationService.searchRegistrationByEmail(email);
            if(commonMsgDtl.isSuccess()){
                userList = (List<Users>)commonMsgDtl.getObjList();
                commonMsgDtl.setContent(commonUtil.gsonToJsonString(userList));
                log.debug("RegistrationController | searchRegistration | registrations : \n" + commonUtil.gsonToJsonString(userList));
            }else{
                log.error("RegistrationController | searchRegistration | registrations : " + commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            log.error("RegistrationController | searchRegistration | Error msg : " + ex.getMessage());
        }finally{
            log.debug("RegistrationController | searchRegistration | End ");
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PutMapping("/change-password.do")
    public ResponseEntity<String> changePassword(HttpServletRequest request){
        log.debug("RegistrationController | changePassword | Start");
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            String email = request.getParameter("email");
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");
            commonMsgDtl = registrationService.changePassword(email, newPassword, confirmPassword);
            if(commonMsgDtl.isSuccess()){
                log.debug("RegistrationController | changePassword | getContent : " + commonMsgDtl.getContent());
            }else{
                log.error("RegistrationController | changePassword | getContent : " + commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            log.error("RegistrationController changePassword | Error msg : " + ex.getMessage());
        }finally{
            log.debug("RegistrationController | changePassword | End ");
        }
        return new ResponseEntity<>(commonMsgDtl.getContent(), HttpStatus.OK);
    }

    @DeleteMapping("/delete-account.do")
    public ResponseEntity<String> deleteAccount(HttpServletRequest request){
        log.debug("RegistrationController | deactivateAccount | Start");
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            commonMsgDtl = registrationService.deleteAccount(email, password);
            log.debug("RegistrationController | deactivateAccount | getContent : " + commonMsgDtl.getContent());
        }catch(Exception ex){
            log.error("RegistrationController | deactivateAccount | Error : " + ex.getMessage());
        }
        return new ResponseEntity<>(commonMsgDtl.getContent(), HttpStatus.OK);
    }
}
