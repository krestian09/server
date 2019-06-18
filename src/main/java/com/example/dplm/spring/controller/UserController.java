package com.example.dplm.spring.controller;

import com.example.dplm.domain.exception.UserValidationException;
import com.example.dplm.domain.exception.UserVerifyException;
import com.example.dplm.domain.model.NewUser;
import com.example.dplm.domain.model.User;
import com.example.dplm.spring.controller.models.UserVerifyResult;
import com.example.dplm.spring.mapper.NewUserMapper;
import com.example.dplm.spring.service.TokenHandler;
import com.example.dplm.spring.service.UserService;
import com.example.dplm.usecase.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/sign/up")
    public UserVerifyResult registration(@RequestBody NewUser user, BindingResult bindingResult) {
        UserVerifyResult.UserVerifyResultBuilder result = UserVerifyResult.builder();
        if (bindingResult.hasErrors()) {
            return result.error(bindingResult.getAllErrors().toString()).build();
        }
        try {
            return result.token(userService.save(NewUserMapper.toDomainUser(user))).build();
        } catch (UserValidationException e) {
            return result.error(e.getMessage()).build();
        }
    }

    @PostMapping("/sign/in")
    public UserVerifyResult signIn(@RequestBody User user, BindingResult bindingResult) {
        UserVerifyResult.UserVerifyResultBuilder result = UserVerifyResult.builder();
        if (bindingResult.hasErrors()) {
            return result.error(bindingResult.getAllErrors().toString()).build();
        }
        try{
            return result.token(userService.verify(user)).build();
        } catch (UserVerifyException e){
            return result.error(e.getMessage()).build();
        }

    }
}
