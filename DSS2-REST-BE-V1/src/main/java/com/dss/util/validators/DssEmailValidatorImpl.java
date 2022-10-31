/**
 * This validator class checks if the email is valid or not.
 * @package com.dss.util.validators
 * @class DssEmailValidatorImpl
 * @author Glen Mark T Anduiza
 */

package com.dss.util.validators;

import com.dss.util.utils.CommonStringUtility;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class DssEmailValidatorImpl implements ConstraintValidator<DssEmailValidator, String> {

    /**
     * initializes Constrain Validator
     * @param constraintAnnotation is an DssEmailValidator interface
     * @see #initialize(DssEmailValidator)
     */

    @Override
    public void initialize(DssEmailValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * returns a boolean value if email is valid or not.
     * @param email user's email address
     * @param constraintValidatorContext is an ConstraintValidatorContext interface
     * @return a boolean value
     * @see #isValid(String ,ConstraintValidatorContext)
     */

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.compile(CommonStringUtility.REGEX_PATTERN_EMAIL)
                .matcher(email)
                .matches();
    }
}
