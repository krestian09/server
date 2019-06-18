package com.example.dplm.spring.service;

import com.example.dplm.domain.exception.UserValidationException;
import com.example.dplm.domain.exception.UserVerifyException;
import com.example.dplm.domain.model.Token;
import com.example.dplm.spring.db.Role;
import com.example.dplm.spring.db.User;
import com.example.dplm.spring.repos.RoleRepository;
import com.example.dplm.spring.repos.UserRepository;
import com.example.dplm.usecase.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private static final long YEARS = 20;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TokenHandler tokenHandler;
    private UserValidator userValidator = new UserValidator();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public Token save(final User newUser) throws UserValidationException {
        Role role = roleRepository.findUserRole();
        newUser.setAuthorities(Collections.singletonList(role));
        userValidator.validateCreateUser(newUser);
        if (userRepository.findByUsername(newUser.getUsername()) != null)
            throw new UserValidationException("Username exist");
        User user = userRepository.save(newUser);
        return new Token(tokenHandler.generateAccessToken(user.getId(), getExpireTime()));
    }

    public Token verify(final com.example.dplm.domain.model.User possibleUser) throws UserVerifyException {
        User user = userRepository.findByUsername(possibleUser.getUsername());
        userValidator.checkUser(possibleUser, user);
        return new Token(tokenHandler.generateAccessToken(user.getId(), getExpireTime()));
    }

    private LocalDateTime getExpireTime() {
        return LocalDateTime.now().plusYears(YEARS);
    }

}
