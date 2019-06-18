package com.example.dplm.spring.mapper;

import com.example.dplm.spring.db.User;
import com.example.dplm.domain.model.NewUser;

import java.util.ArrayList;

public class NewUserMapper {

    public static User toDomainUser(NewUser newUser){
        return User.builder().accountNonExpired(true)
                .accountNonLocked(true)
                .active(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .password(newUser.getPassword())
                .username(newUser.getUsername()).build();
    }
}
