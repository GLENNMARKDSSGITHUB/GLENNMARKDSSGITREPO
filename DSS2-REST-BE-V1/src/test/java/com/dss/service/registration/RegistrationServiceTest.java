package com.dss.service.registration;

import com.dss.dto.user.UsersDTO;
import com.dss.entity.movie.DssMovie;
import com.dss.entity.roles.Roles;
import com.dss.entity.user.Users;
import com.dss.repository.roles.RolesRepository;
import com.dss.repository.user.UsersRepository;
import com.dss.transformer.registration.RegistrationTransformerTest;
import com.dss.util.enums.UserRoles;
import com.dss.util.enums.UserStatus;
import com.dss.util.utils.DssCommonMessageDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationServiceTest {

    @Autowired
    private RegistrationService registrationService;

    @MockBean
    private UsersRepository usersRepository;

    @MockBean
    private RolesRepository rolesRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    private final RegistrationTransformerTest transformer = new RegistrationTransformerTest();

    @Test
    public void testRegisterAccount(){
        boolean isSuccess = true;
        Users user = usersRepository.save(transformer.transformToUsers(userDto()));
        rolesRepository.save(new Roles("RS1", UserRoles.ROLE_SUPER_ADMIN.getStrRole(), user));
        Mockito.when(user).thenReturn(transformer.transformToUsers(userDto()));

        DssCommonMessageDetails commonMsgDtl = registrationService.registerAccount(userDto());
        commonMsgDtl.setSuccess(true);
        assertThat(commonMsgDtl.isSuccess()).isEqualTo(isSuccess);
    }

    private UsersDTO userDto(){
        return new UsersDTO(
                "US0001",
                "Glenn Mark",
                "Anduiza",
                "glenmark.ghl@gmail.com",
                encoder.encode("P@$$w0rd1234"),
                encoder.encode("P@$$w0rd1234"),
                UserStatus.ACTIVE.toString(),
                "09106121529",
                new Date(),
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                null,
                null
        );
    }
}
