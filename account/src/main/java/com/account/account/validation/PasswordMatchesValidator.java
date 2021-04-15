package com.account.account.validation;

import com.account.account.view.UserView;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserView user = (UserView) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
