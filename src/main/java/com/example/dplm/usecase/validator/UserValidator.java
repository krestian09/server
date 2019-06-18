package com.example.dplm.usecase.validator;

import com.example.dplm.domain.exception.UserValidationException;
import com.example.dplm.domain.exception.UserVerifyException;


import com.example.dplm.spring.db.User;
import org.apache.commons.lang3.StringUtils;

public class UserValidator {

    public void validateCreateUser(final User newUser) throws UserValidationException {
        if (newUser == null)
            throw new UserValidationException("NewUser should not be null");
        if (StringUtils.isBlank(newUser.getUsername()))
            throw new UserValidationException("Username should not be null");
        if (StringUtils.isBlank(newUser.getPassword()))
            throw new UserValidationException("Password should not be null");
    }

    public void checkUser(com.example.dplm.domain.model.User possibleUser, com.example.dplm.spring.db.User user) throws UserVerifyException {
        if (user == null)
            throw new UserVerifyException("User not found");
        if (StringUtils.isBlank(possibleUser.getUsername()))
            throw new UserVerifyException("Username should not be null");
        if (StringUtils.isBlank(possibleUser.getPassword()))
            throw new UserVerifyException("Password should not be null");
        if (!user.getUsername().equals(possibleUser.getUsername()))
            throw new UserVerifyException("User not found");
        if (!user.getPassword().equals(possibleUser.getPassword()))
            throw new UserVerifyException("Password do not match");
    }

    public UserValidator() {

    }
}
