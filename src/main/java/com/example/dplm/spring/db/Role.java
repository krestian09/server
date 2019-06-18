package com.example.dplm.spring.db;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
@Data
public class Role implements GrantedAuthority  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
