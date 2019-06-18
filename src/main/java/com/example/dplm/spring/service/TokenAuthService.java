package com.example.dplm.spring.service;

import com.example.dplm.spring.auth.UserAuthentication;
import com.example.dplm.spring.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class TokenAuthService {
    private static final String AUTH_HEADER_NAME = "X-Auth-Token";
    @Autowired
    private TokenHandler tokenHandler;
    @Autowired
    private UserRepository userRepository;

    public Optional<Authentication> getAuthentication(HttpServletRequest request){
        return Optional.ofNullable(request.getHeader(AUTH_HEADER_NAME))
                .flatMap(tokenHandler::extract)
                .flatMap(userRepository::findById)
                .map(UserAuthentication::new);
    }
}
