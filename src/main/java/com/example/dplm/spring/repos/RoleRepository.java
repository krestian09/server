package com.example.dplm.spring.repos;

import com.example.dplm.spring.db.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "Select * from user_role where name = user", nativeQuery = true)
    Role findUserRole();

    @Query(value = "Select * from user_role where name = admin", nativeQuery = true)
    Role findAdminRole();
}
