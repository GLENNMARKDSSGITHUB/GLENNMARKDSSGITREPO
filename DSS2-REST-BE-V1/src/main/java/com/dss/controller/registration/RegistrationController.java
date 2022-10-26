package com.dss.controller.registration;

import com.dss.dto.UsersDTO;
import com.dss.entity.Users;
import com.dss.service.registration.RegistrationService;
import com.dss.util.common.DigiStreamCommonMessageDetails;
import com.dss.util.common.DigiStreamCommonUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/API/registration")
public class RegistrationController {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    private final DigiStreamCommonUtility commonUtil = new DigiStreamCommonUtility();
    private DigiStreamCommonMessageDetails commonMsgDtls = new DigiStreamCommonMessageDetails();

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/add-registration.do")
    public String addRegistration(@RequestBody UsersDTO userDto){
        DigiStreamCommonMessageDetails commonMsgDtl = new DigiStreamCommonMessageDetails();
        logger.debug("RegistrationServiceImpl | addRegistration | userDTO: " + userDto);
        try{
            commonMsgDtl = registrationService.addRegistration(userDto);
            logger.debug(commonMsgDtl.getContent());
        }catch (Exception ex){
            logger.error("RegistrationController | addRegistration | Error msg : " + ex.getMessage());
        }
        return commonMsgDtl.getContent();
    }

    @GetMapping("/display-registrations.do")
    public List<Users> displayRegistration(){
        DigiStreamCommonMessageDetails commonMsgDtl = registrationService.displayRegistrations();
        List<Users> userList = null;
        try{
            if(commonMsgDtl.isSuccess()){
                userList = (List<Users>)commonMsgDtl.getObjList();
                logger.debug(commonUtil.gsonToJsonString(userList));
            }else{
                logger.error(commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            logger.error("RegistrationController | displayRegistration | Error msg : " + ex.getMessage());
        }
        return userList;
    }

    @GetMapping("/search-registration.do")
    public List<Users> searchRegistration(HttpServletRequest request){
        logger.debug("RegistrationController | searchRegistration | Email : " + request.getParameter("email"));
        List<Users> userList = null;
        try{
            DigiStreamCommonMessageDetails commonMsgDtl = registrationService.searchRegistrationByEmail(request.getParameter("email"));
            if(commonMsgDtl.isSuccess()){
                userList = (List<Users>)commonMsgDtl.getObjList();
                logger.debug(commonUtil.gsonToJsonString(userList));
            }
        }catch (Exception ex){
            logger.error("RegistrationController | displayRegistration | Error msg : " + ex.getMessage());
        }
        return userList;
    }

    @PutMapping("/update-registration.do")
    public String updateRegistration(@RequestBody UsersDTO userDto){
        DigiStreamCommonMessageDetails commonMsgDtl = new DigiStreamCommonMessageDetails();
        logger.debug("RegistrationController | updateRegistration | UserDTO : " + userDto.toString());
        try{
            commonMsgDtl = registrationService.updateRegistration(userDto);
            logger.debug(commonMsgDtl.getContent());
        }catch (Exception ex){
            logger.error("RegistrationController | displayRegistration | Error msg : " + ex.getMessage());
        }
        return commonMsgDtl.getContent();
    }

    @DeleteMapping("/delete-registration.do")
    public String deleteRegistration(HttpServletRequest request){
        logger.debug("RegistrationController | deleteRegistration | Email : " + request.getParameter("email"));
        logger.debug("RegistrationController | deleteRegistration | Password : " + request.getParameter("password"));
        try{
            commonMsgDtls = registrationService.deleteRegistration(request.getParameter("email"), request.getParameter("password"));
            logger.debug(commonMsgDtls.getContent());
        }catch(Exception ex){
            logger.error("RegistrationController | deleteRegistration | Error : " + ex.getMessage());
        }
        return commonMsgDtls.getContent();
    }
}
