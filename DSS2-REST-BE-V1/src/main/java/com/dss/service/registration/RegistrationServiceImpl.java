/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.service.registration;

import com.dss.dto.user.UsersDTO;
import com.dss.entity.roles.Roles;
import com.dss.entity.user.Users;
import com.dss.repository.user.UsersRepository;
import com.dss.repository.roles.RolesRepository;
import com.dss.transformer.registration.RegistrationTransformer;
import com.dss.util.utils.DssCommonMessageDetails;
import com.dss.util.utils.CommonStringUtility;
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
        }catch(Exception ex){
            commonMsgDtl.setSuccess(false);
            logger.error("RegistrationServiceImpl | addRegistration | Error : {}", ex.getMessage());
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
            }
        }catch (Exception ex){
            commonMsgDtl.setSuccess(false);
            logger.error("RegistrationServiceImpl | displayRegistrations | Error : {}", ex.getMessage());
        }finally{
            logger.debug("RegistrationServiceImpl | displayRegistrations | End ");
        }
        return commonMsgDtl;
    }
}
