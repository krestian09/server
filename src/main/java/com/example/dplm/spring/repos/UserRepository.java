package com.example.dplm.spring.repos;

import com.example.dplm.spring.db.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    Optional<User> findById(Long id);
}
