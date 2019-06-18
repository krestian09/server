package com.example.dplm.spring.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usr", uniqueConstraints = @UniqueConstraint(columnNames={"username"}))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;
    private boolean active;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    @OneToMany
    @JoinTable(name="user_roles",
            joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
    )
    private List<Role> authorities;
}
