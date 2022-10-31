package com.dss.controller.registration;

import com.dss.dto.user.UsersDTO;
import com.dss.entity.user.Users;
import com.dss.service.registration.RegistrationService;
import com.dss.util.utils.DssCommonMessageDetails;
import com.dss.util.utils.DssCommonUtility;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/API/registration")
@Slf4j
public class RegistrationController {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    private final DssCommonUtility commonUtil = new DssCommonUtility();

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/add-registration.do")
    public String addRegistration(@Valid @RequestBody UsersDTO userDto, BindingResult result){
        logger.debug("RegistrationController | addRegistration | Start");
        logger.debug("RegistrationController | addRegistration | userDTO: " + userDto.toString());
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            if(result.hasErrors()){
                StringBuilder addStr = new StringBuilder();
                List<ObjectError> errList = result.getAllErrors();
                for(ObjectError err : errList){
                    logger.error("RegistrationController | addRegistration | Error msg : " + err.getDefaultMessage());
                    addStr.append(err.getDefaultMessage()).append("\n");
                }
                commonMsgDtl.setContent(addStr.toString());
            }else{
                commonMsgDtl = registrationService.registerAccount(userDto);
                logger.debug(commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            logger.error("RegistrationController | addRegistration | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("RegistrationController | addRegistration | End ");
        }
        return commonMsgDtl.getContent();
    }

    @GetMapping("/display-registrations.do")
    public String displayRegistration(){
        logger.debug("RegistrationController | displayRegistration | Start");
        DssCommonMessageDetails commonMsgDtl = registrationService.displayRegistrations();
        try{
            if(commonMsgDtl.isSuccess()){
                List<Users> userList = (List<Users>)commonMsgDtl.getObjList();
                commonMsgDtl.setContent(commonUtil.gsonToJsonString(userList));
                logger.debug("RegistrationController | displayRegistration | registrations : \n" + commonUtil.gsonToJsonString(userList));
            }else{
                logger.error("RegistrationController | displayRegistration | registrations : " + commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            logger.error("RegistrationController | displayRegistration | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("RegistrationController | displayRegistration | End ");
        }
        return commonMsgDtl.getContent();
    }

    @GetMapping("/search-registration.do")
    public String searchRegistration(HttpServletRequest request){
        logger.debug("RegistrationController | searchRegistration | Start");
        String email = request.getParameter("email");
        DssCommonMessageDetails commonMsgDtl = registrationService.searchRegistrationByEmail(email);
        try{
            if(commonMsgDtl.isSuccess()){
                List<Users> userList = (List<Users>)commonMsgDtl.getObjList();
                commonMsgDtl.setContent(commonUtil.gsonToJsonString(userList));
                logger.debug("RegistrationController | searchRegistration | registrations : \n" + commonUtil.gsonToJsonString(userList));
            }else{
                logger.error("RegistrationController | searchRegistration | registrations : " + commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            logger.error("RegistrationController | searchRegistration | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("RegistrationController | searchRegistration | End ");
        }
        return commonMsgDtl.getContent();
    }

    @PutMapping("/change-password.do")
    public String changePassword(HttpServletRequest request){
        logger.debug("RegistrationController | changePassword | Start");
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        String email = request.getParameter("email");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        try{
            commonMsgDtl = registrationService.changePassword(email, newPassword, confirmPassword);
            if(commonMsgDtl.isSuccess()){
                logger.debug("RegistrationController | changePassword | getContent : " + commonMsgDtl.getContent());
            }else{
                logger.error("RegistrationController | changePassword | getContent : " + commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            logger.error("RegistrationController changePassword | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("RegistrationController | changePassword | End ");
        }
        return commonMsgDtl.getContent();
    }

    @DeleteMapping("/delete-account.do")
    public String deleteAccount(HttpServletRequest request){
        logger.debug("RegistrationController | deactivateAccount | Start");
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try{
            commonMsgDtl = registrationService.deleteAccount(email, password);
            logger.debug("RegistrationController | deactivateAccount | getContent : " + commonMsgDtl.getContent());
        }catch(Exception ex){
            logger.error("RegistrationController | deactivateAccount | Error : " + ex.getMessage());
        }
        return commonMsgDtl.getContent();
    }
}
