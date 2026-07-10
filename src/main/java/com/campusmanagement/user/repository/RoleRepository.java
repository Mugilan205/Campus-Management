package com.campusmanagement.user.repository;

import com.campusmanagement.common.enums.RoleType;
import com.campusmanagement.user.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findByName(RoleType name);
    boolean existsByName(RoleType name);

}