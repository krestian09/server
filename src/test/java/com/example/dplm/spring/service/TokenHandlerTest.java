package com.example.dplm.spring.service;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Optional;

public class TokenHandlerTest {

    @Test
    public void generateToken(){
        TokenHandler tokenHandler = new TokenHandler();
        String token = tokenHandler.generateAccessToken(1, LocalDateTime.now().plusDays(14));
        System.out.println(token);

        Optional<Long> id = tokenHandler.extract(token);
        System.out.println(id.get().toString());
    }

}