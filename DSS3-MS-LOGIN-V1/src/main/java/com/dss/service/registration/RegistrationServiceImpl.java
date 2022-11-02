/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.service.registration;

import com.dss.dto.user.UsersDTO;
import com.dss.entity.roles.Roles;
import com.dss.entity.user.Users;
import com.dss.repository.roles.RolesRepository;
import com.dss.repository.user.UsersRepository;
import com.dss.transformer.RegistrationTransformer;
import com.dss.util.utils.CommonStringUtility;
import com.dss.util.utils.DssCommonMessageDetails;
import com.dss.util.utils.DssCommonMethods;
import com.dss.util.utils.DssCommonUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is a service implementation for DSS Account Registration
 * @see #registerAccount(UsersDTO)
 * @see #displayRegistrations()
 * @see #searchRegistrationByEmail(String)
 * @see #changePassword(String,String,String)
 * @see #changePassword(String,String,String)
 */

@Service
public class RegistrationServiceImpl implements RegistrationService{
    private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final DssCommonMethods commonMethods = new DssCommonMethods();
    private final RegistrationTransformer transformer = new RegistrationTransformer();
    private final DssCommonUtility util = new DssCommonUtility();

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public DssCommonMessageDetails registerAccount(UsersDTO userDto){
        logger.debug("RegistrationServiceImpl | addRegistration | Start ");
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            String dssUserId = commonMethods.userIdGeneration(userRepository.maxUserId());
            userDto.setDssUserId(dssUserId);
            Users user = transformer.populateUsersRegistration(userDto);
            userRepository.save(user);
            Roles role = transformer.populateRolesRegistration(userDto, user).get(0);
            rolesRepository.save(role);
            commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_CODE_001_ADD_REG, userDto.getEmail()));
            commonMsgDtl.setSuccess(true);
            logger.debug("RegistrationServiceImpl | addRegistration | getContent : " + commonMsgDtl.getContent());
        }catch(Exception ex){
            commonMsgDtl.setSuccess(false);
            logger.error("RegistrationServiceImpl | addRegistration | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("RegistrationServiceImpl | addRegistration | End ");
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails displayRegistrations() {
        logger.debug("RegistrationServiceImpl | displayRegistrations | Start ");
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            List<Users> userList = userRepository.findAll();
            if(!userList.isEmpty()){
                commonMsgDtl.setObjList(transformer.populateUsersRegistration(userList));
                commonMsgDtl.setSuccess(true);
            }else{
                commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_002_NO_DISPLAY_RECORDS);
                commonMsgDtl.setSuccess(false);
                logger.debug("RegistrationServiceImpl | displayRegistrations | getContent : " + commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            logger.error("RegistrationServiceImpl | displayRegistrations | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("RegistrationServiceImpl | displayRegistrations | End ");
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails searchRegistrationByEmail(String email){
        logger.debug("RegistrationServiceImpl | searchRegistrationByEmail | Start ");
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            List<Users> userList = userRepository.findUserByEmail(email);
            if(!userList.isEmpty()){
                commonMsgDtl.setObjList(transformer.populateUsersRegistration((userList)));
                commonMsgDtl.setSuccess(true);
            }else{
                commonMsgDtl.setContent(String.format(CommonStringUtility.ERR_CODE_003_NO_RECORDS_FOUND, email));
                commonMsgDtl.setSuccess(false);
                logger.debug("RegistrationServiceImpl | searchRegistrationByEmail | getContent : " + commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            logger.error("RegistrationServiceImpl | searchRegistrationByEmail | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("RegistrationServiceImpl | searchRegistrationByEmail | End ");
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails changePassword(String email, String newPassword, String confirmPassword) {
        logger.debug("RegistrationServiceImpl | updateUsersPassword | Start ");
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            List<Users> userList = userRepository.findUserByEmail(email);
            if(!userList.isEmpty()){
                if(newPassword.equalsIgnoreCase(confirmPassword)){
                    Users user = userList.get(0);
                    if(util.isNullOrEmpty(user.getOldPassword())){
                        user.setPassword(newPassword);
                        user.setOldPassword(confirmPassword);
                        userRepository.save(transformer.populateUsersRegistration(user));
                        commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_CODE_004_UPDATE_REG));
                        commonMsgDtl.setSuccess(true);
                        logger.debug("RegistrationServiceImpl | updateRegistration | getContent : " + commonMsgDtl.getContent());
                    }else{
                        if(encoder.matches(newPassword, user.getOldPassword())){
                            commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_CODE_003_SAME_PASSWORD));
                            commonMsgDtl.setSuccess(false);
                            logger.error("RegistrationServiceImpl | updateRegistration | getContent : " + commonMsgDtl.getContent());
                        }else{
                            user.setPassword(newPassword);
                            user.setOldPassword(confirmPassword);
                            userRepository.save(transformer.populateUsersRegistration(user));
                            commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_CODE_004_UPDATE_REG));
                            commonMsgDtl.setSuccess(true);
                            logger.debug("RegistrationServiceImpl | updateRegistration | getContent : " + commonMsgDtl.getContent());
                        }
                    }
                }else{
                    commonMsgDtl.setContent(String.format(CommonStringUtility.ERR_CODE_005_PASSWORD_NOT_MATCH));
                    commonMsgDtl.setSuccess(false);
                    logger.error("RegistrationServiceImpl | updateRegistration | getContent : " + commonMsgDtl.getContent());
                }
            }else{
                commonMsgDtl.setContent(String.format(CommonStringUtility.ERR_CODE_004_ACCT_NOT_EXISTING));
                commonMsgDtl.setSuccess(false);
                logger.error("RegistrationServiceImpl | updateRegistration | getContent : " + commonMsgDtl.getContent());
            }
        }catch(Exception ex){
            logger.error("RegistrationServiceImpl | updateUsersPassword | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("RegistrationServiceImpl | updateUsersPassword | End ");
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails deleteAccount(String email, String password){
        logger.debug("RegistrationServiceImpl | deactivateAccount | Start ");
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            List<Users> userList = userRepository.findUserByEmail(email);
            if(!userList.isEmpty()){
                if(encoder.matches(password, userList.get(0).getPassword())){
                    Roles role = userList.get(0).getUserRoles().get(0);
                    rolesRepository.delete(role);
                    Users user = userList.get(0);
                    userRepository.delete(user);
                    commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_CODE_003_DELETE_REG, email));
                    commonMsgDtl.setSuccess(true);
                    logger.debug("RegistrationServiceImpl | deactivateAccount | getContent : " + commonMsgDtl.getContent());
                }else{
                    commonMsgDtl.setSuccess(false);
                    commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_005_PASSWORD_NOT_MATCH);
                    logger.error("RegistrationServiceImpl | deactivateAccount | getContent : " + commonMsgDtl.getContent());
                }
            }else{
                commonMsgDtl.setSuccess(false);
                commonMsgDtl.setContent(String.format(CommonStringUtility.ERR_CODE_003_NO_RECORDS_FOUND, email));
                logger.error("RegistrationServiceImpl | deactivateAccount | getContent : " + commonMsgDtl.getContent());
            }
        }catch(Exception ex){
            logger.error("RegistrationServiceImpl | deactivateAccount | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("RegistrationServiceImpl | deactivateAccount | End ");
        }
        return commonMsgDtl;
    }
}
