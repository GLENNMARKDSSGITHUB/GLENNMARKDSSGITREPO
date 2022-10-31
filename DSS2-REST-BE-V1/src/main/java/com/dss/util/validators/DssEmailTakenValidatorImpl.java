/**
 * This validator class checks if the email id is already in use by some other users.
 * @package com.dss.util.validators
 * @class DssEmailTakenValidatorImpl
 * @author Glen Mark T Anduiza
 */

package com.dss.util.validators;

import com.dss.entity.user.Users;
import com.dss.repository.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class DssEmailTakenValidatorImpl implements ConstraintValidator<DssEmailTakenValidator, String> {

    @Autowired
    private UsersRepository userRepository;

    /**
     * initializes Constrain Validator
     * @param constraintAnnotation is an DssEmailTakenValidator interface
     * @see #initialize(DssEmailTakenValidator)
     */

    @Override
    public void initialize(DssEmailTakenValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * returns a boolean value if the email id is existing or not
     * @param email user's email ID
     * @param constraintValidatorContext is an ConstraintValidatorContext interface
     * @return a boolean value
     * @see #isValid(String ,ConstraintValidatorContext)
     */

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        List<Users> userEmailList = userRepository.findUserByEmail(email);
        return userEmailList.isEmpty();
    }
}
