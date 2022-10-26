package com.dss.service.registration;

import com.dss.dto.RolesDTO;
import com.dss.dto.UsersDTO;
import com.dss.entity.Roles;
import com.dss.entity.Users;
import com.dss.repository.RegistrationRepository;
import com.dss.repository.RolesRepository;
import com.dss.util.common.DigiStreamCommonMessageDetails;
import com.dss.util.common.DigiStreamCommonMethods;
import com.dss.util.common.CommonStringUtility;
import com.dss.util.common.DigiStreamCommonUtility;
import com.sun.istack.NotNull;
import org.jetbrains.annotations.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService{
    private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    private final DigiStreamCommonUtility commonUtil = new DigiStreamCommonUtility();
    private final DigiStreamCommonMessageDetails commonMsgDtls = new DigiStreamCommonMessageDetails();

    private final DigiStreamCommonMethods digiStreamCommonMethods = new DigiStreamCommonMethods();

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private RegistrationRepository regRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public DigiStreamCommonMessageDetails addRegistration(UsersDTO userDto){
        DigiStreamCommonMessageDetails commonMsgDtl = this.validateUsersFields(userDto);
        try{
            if(commonMsgDtl.isSuccess()){
                userDto.getUserRoles().get(0).setDssRoleId(this.dssIdGeneration());
                rolesRepository.save(this.populateRolesRegistration(userDto).get(0));
                userDto.setDssUserId(this.dssIdGeneration());
                regRepository.save(this.populateUsersRegistration(userDto));
                commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_CODE_001_ADD_REG, userDto.getEmail()));
                commonMsgDtl.setSuccess(true);
            }else{
                logger.error("RegistrationServiceImpl | addRegistration | getContent : " + commonMsgDtl.getContent());
            }
        }catch(Exception ex){
            logger.error("RegistrationServiceImpl | addRegistration | Error msg : " + ex.getMessage());
        }
        return commonMsgDtl;
    }

    @Override
    public DigiStreamCommonMessageDetails displayRegistrations() {
        DigiStreamCommonMessageDetails commonMsgDtl = new DigiStreamCommonMessageDetails();
        try{
            List<Users> userList = regRepository.findAll();
            if(!userList.isEmpty()){
                commonMsgDtl.setObjList(populateUsersDTORegistration(userList));
                commonMsgDtl.setSuccess(true);
            }else{
                commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_002_NO_DISPLAY_RECORDS);
                commonMsgDtl.setSuccess(false);
            }
        }catch (Exception ex){
            logger.error("RegistrationServiceImpl | displayRegistrations | Error msg : " + ex.getMessage());
        }
        return commonMsgDtl;
    }

    @Override
    public DigiStreamCommonMessageDetails searchRegistrationByEmail(String email){
        DigiStreamCommonMessageDetails commonMsgDtl = new DigiStreamCommonMessageDetails();
        try{
            List<Users> userList = regRepository.findUserByEmail(email);
            if(!userList.isEmpty()){
                commonMsgDtl.setObjList(populateUsersDTORegistration((userList)));
                commonMsgDtl.setSuccess(true);
            }else{
                commonMsgDtl.setContent(String.format(CommonStringUtility.ERR_CODE_003_NO_RECORDS_FOUND, email));
                commonMsgDtl.setSuccess(false);
            }
        }catch (Exception ex){
            logger.error("RegistrationServiceImpl | searchRegistrationByEmail | Error msg : " + ex.getMessage());
        }
        return commonMsgDtl;
    }

    @Override
    public DigiStreamCommonMessageDetails updateRegistration(UsersDTO userDto){
        DigiStreamCommonMessageDetails commonMsgDtl = new DigiStreamCommonMessageDetails();
        List<Users> userEmailList = regRepository.findUserByEmail(userDto.getEmail());
        try{
            if(!userEmailList.isEmpty()){
                rolesRepository.save(this.populateRolesRegistration(userDto).get(0));
                regRepository.save(this.populateUsersRegistration(userDto));
                commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_CODE_002_UPDATE_REG, userDto.getEmail()));
                commonMsgDtl.setSuccess(true);
                logger.debug("RegistrationServiceImpl | updateRegistration | getContent : " + commonMsgDtl.getContent());
            }else{
                commonMsgDtl.setContent(String.format(CommonStringUtility.ERR_CODE_003_NO_RECORDS_FOUND, userDto.getEmail()));
                commonMsgDtl.setSuccess(false);
                logger.error("RegistrationServiceImpl | updateRegistration | getContent : " + commonMsgDtl.getContent());
            }
        }catch(Exception ex){
            logger.error("RegistrationServiceImpl | updateRegistration | Error msg : " + ex.getMessage());
        }
        return commonMsgDtl;
    }

    @Override
    public DigiStreamCommonMessageDetails deleteRegistration(String email, String password){
        DigiStreamCommonMessageDetails commonMsgDtl = new DigiStreamCommonMessageDetails();
        try{
            List<Users> userList = regRepository.findUserByEmail(email);
            if(!userList.isEmpty()){
                if(encoder.matches(password, userList.get(0).getPassword())){
                    regRepository.delete(userList.get(0));
                    rolesRepository.delete(userList.get(0).getUserRoles().get(0));
                    commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_CODE_002_DELETE_REG, email));
                    commonMsgDtl.setSuccess(true);
                }else{
                    commonMsgDtl.setSuccess(false);
                    commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_001_INVALID_EMAIL);
                    logger.error("RegistrationServiceImpl | updateRegistration | getContent : " + commonMsgDtl.getContent());
                }
            }else{
                commonMsgDtl.setSuccess(false);
                commonMsgDtl.setContent(String.format(CommonStringUtility.ERR_CODE_003_NO_RECORDS_FOUND, email));
                logger.error("RegistrationServiceImpl | updateRegistration | getContent : " + commonMsgDtl.getContent());
            }
        }catch(Exception ex){
            logger.error("RegistrationServiceImpl | updateRegistration | Error msg : " + ex.getMessage());
        }
        return commonMsgDtl;
    }

    private DigiStreamCommonMessageDetails validateUsersFields(UsersDTO userDto){
        logger.debug("RegistrationServiceImpl | validateUsersFields | userDTO: " + userDto);

        DigiStreamCommonMessageDetails commonMsgDtl = new DigiStreamCommonMessageDetails();
        List<Users> userEmailList = regRepository.findUserByEmail(userDto.getEmail());
        List<Users> userCellphoneNoList = regRepository.findUserByCellphoneNumber(userDto.getCellphoneNumber());
        String fullName = userDto.getFirstName() + " " + userDto.getLastName();

        //To check if details are not filled.
        if(!commonUtil.isNullOrEmpty(userDto.getFirstName()) && !commonUtil.isNullOrEmpty(userDto.getLastName())
                && !commonUtil.isNullOrEmpty(userDto.getEmail()) && !commonUtil.isNullOrEmpty(userDto.getPassword())
                && !commonUtil.isNullOrEmpty(userDto.getStatus()) && !commonUtil.isNullOrEmpty(userDto.getCellphoneNumber())
                && !commonUtil.isNullOrEmpty(userDto.getUserRoles().get(0).getUserRole())){
            //To check if email is already in use by some other users
            if(userEmailList.isEmpty()){
                //To check if user's full name containing numbers and special characters.
                if(!commonUtil.patternMatches(fullName, CommonStringUtility.REGEX_PATTERN_SPECIAL_CHAR_NUM)){
                    //To check if user's email address is correct.
                    if(commonUtil.patternMatches(userDto.getEmail(), CommonStringUtility.REGEX_PATTERN_EMAIL)){
                        //To check if user's full name containing at least an uppercase and lowercase alphabet, a digit and a special character.
                        if(commonUtil.patternMatches(userDto.getPassword(), CommonStringUtility.REGEX_PATTERN_PASSWORD)){
                            //To check if user's cellphone number is existing
                            if(userCellphoneNoList.isEmpty()){
                                commonMsgDtl.setSuccess(true);
                            } else{
                                commonMsgDtl.setContent(String.format(CommonStringUtility.ERR_CODE_001_CELL_NO_TAKEN, userDto.getCellphoneNumber()));
                                commonMsgDtl.setSuccess(false);
                            }
                        }else{
                            commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_001_PASSWORD_ALLOWED);
                            commonMsgDtl.setSuccess(false);
                        }
                    }else{
                        commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_001_INVALID_EMAIL);
                        commonMsgDtl.setSuccess(false);
                    }
                }else{
                    commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_001_ALPHABET_ALLOWED);
                    commonMsgDtl.setSuccess(false);
                }
            }else{
                commonMsgDtl.setContent(String.format(CommonStringUtility.ERR_CODE_001_EMAIL_TAKEN, userDto.getEmail()));
                commonMsgDtl.setSuccess(false);
            }
        }else{
            commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_001_CHECK_FIELDS);
            commonMsgDtl.setSuccess(false);
        }
        return commonMsgDtl;
    }

    private List<Roles> populateRolesRegistration(UsersDTO userDto){
        return Collections.singletonList(new Roles(
                userDto.getUserRoles().get(0).getDssRoleId(),
                userDto.getUserRoles().get(0).getUserRole(),
                userDto.getUserRoles().get(0).getCreationDate(),
                userDto.getUserRoles().get(0).getCreatedBy(),
                userDto.getUserRoles().get(0).getLastModificationDate(),
                userDto.getUserRoles().get(0).getLastModifiedBy()));
    }

    private Users populateUsersRegistration(UsersDTO userDto){
        return new Users(
                userDto.getDssUserId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                encoder.encode(userDto.getPassword()),
                userDto.getStatus(),
                userDto.getCellphoneNumber(),
                userDto.getCreationDate(),
                userDto.getCreatedBy(),
                userDto.getLastModificationDate(),
                userDto.getLastModifiedBy(),
                Collections.singletonList(new Roles(
                        userDto.getUserRoles().get(0).getDssRoleId(),
                        userDto.getUserRoles().get(0).getUserRole(),
                        userDto.getUserRoles().get(0).getCreationDate(),
                        userDto.getUserRoles().get(0).getCreatedBy(),
                        userDto.getUserRoles().get(0).getLastModificationDate(),
                        userDto.getUserRoles().get(0).getLastModifiedBy()))
        );
    }

    @Contract("_ -> new")
    private @NotNull List<UsersDTO> populateUsersDTORegistration(@NotNull List<Users> user){
        List<UsersDTO> usersDto = new ArrayList<>();
        for (Users users : user) {
            usersDto.add(new UsersDTO(
                    users.getDssUserId(),
                    users.getFirstName(),
                    users.getLastName(),
                    users.getEmail(),
                    users.getPassword(),
                    users.getStatus(),
                    users.getCellphoneNumber(),
                    users.getCreationDate(),
                    users.getCreatedBy(),
                    users.getLastModificationDate(),
                    users.getLastModifiedBy(),
                    Collections.singletonList(new RolesDTO(
                            users.getUserRoles().get(0).getDssRoleId(),
                            users.getUserRoles().get(0).getUserRole(),
                            users.getUserRoles().get(0).getCreationDate(),
                            users.getUserRoles().get(0).getCreatedBy(),
                            users.getUserRoles().get(0).getLastModificationDate(),
                            users.getUserRoles().get(0).getLastModifiedBy()))
            ));
        }
        return usersDto;
    }

    private String dssIdGeneration(){
        String dssId;
        int dssIdCount = 0;
        int maxDssIdCount = regRepository.maxDssId();
        int maxDssId = maxDssIdCount;
        while (maxDssIdCount != 0) {
            maxDssIdCount /= 10;
            ++dssIdCount;
        }
        maxDssId = maxDssId + 1;
        logger.debug("RegistrationServiceImpl | dssIdGeneration | dssIdCount : " + dssIdCount);
        logger.debug("RegistrationServiceImpl | dssIdGeneration | Generated dssId : " + maxDssId);
        if(dssIdCount == 1){
            dssId = "DSS000".concat(String.valueOf((maxDssId)));
        } else if(dssIdCount == 2){
            dssId = "DSS00".concat(String.valueOf((maxDssId)));
        } else if(dssIdCount == 3){
            dssId = "DSS0".concat(String.valueOf((maxDssId)));
        } else if(dssIdCount == 4){
            dssId = "DSS".concat(String.valueOf((maxDssId)));
        } else{
            dssId = "DSS".concat(String.valueOf((maxDssId)));
        }
        return dssId;
    }
}
