package com.campusmanagement.config;

import com.campusmanagement.common.enums.RoleType;
import com.campusmanagement.user.entity.Roles;
import com.campusmanagement.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        for (RoleType roleType : RoleType.values()) {

            if (!roleRepository.existsByName(roleType)) {

                Roles role = new Roles();

                role.setName(roleType);

                role.setDescription(roleType.name() + " Role");

                roleRepository.save(role);
            }
        }
    }
}