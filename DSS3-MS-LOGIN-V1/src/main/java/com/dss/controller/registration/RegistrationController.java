/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

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
 * This class is a controller layer for DSS Registrations
 */

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/API/registration")
@Slf4j
public class RegistrationController {
    private final DssCommonUtility commonUtil = new DssCommonUtility();

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
            log.error("RegistrationController | addRegistration | Error msg : " + ex.getMessage());
        }finally{
            log.debug("RegistrationController | addRegistration | End ");
        }
        return commonMsgDtl.getContent();
    }

    /** Returns a list of Users
     * @return List<Users>
     * @see #displayRegistration()
     */
    @GetMapping("/display-registrations.do")
    public List<Users> displayRegistration(){
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
        return userList;
    }

    /** Returns a specific user account in a form of List<Users>
     * @param request HttpServletRequest
     * @return List<Users>
     * @see #searchRegistration(HttpServletRequest)
     */
    @GetMapping("/search-registration.do")
    public List<Users> searchRegistration(HttpServletRequest request){
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
        return userList;
    }

    /** Returns a String value if the admin user successfully changes the account password or not.
     * @param request HttpServletRequest
     * @return String
     * @see #changePassword(HttpServletRequest)
     */
    @PutMapping("/change-password.do")
    public String changePassword(HttpServletRequest request){
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
        return commonMsgDtl.getContent();
    }

    /** Returns a String value if the admin user successfully deletes the account registration or not.
     * @param request HttpServletRequest
     * @return String
     * @see #deleteAccount(HttpServletRequest)
     */
    @DeleteMapping("/delete-account.do")
    public String deleteAccount(HttpServletRequest request){
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
        return commonMsgDtl.getContent();
    }
}
