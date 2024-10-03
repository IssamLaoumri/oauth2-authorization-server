package com.github.authorizationserver.repositories;

import com.github.authorizationserver.entities.Role;
import com.github.authorizationserver.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRole(ERole role);
}
