package com.campusmanagement.auth.service.impl;

import com.campusmanagement.auth.dto.request.RegisterRequest;
import com.campusmanagement.auth.dto.response.RegisterResponse;
import com.campusmanagement.auth.mapper.AuthMapper;
import com.campusmanagement.auth.service.AuthService;
import com.campusmanagement.common.enums.RoleType;
import com.campusmanagement.user.entity.Roles;
import com.campusmanagement.user.entity.User;
import com.campusmanagement.user.repository.RoleRepository;
import com.campusmanagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;
    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user = authMapper.toEntity(request); //req to entity mapping

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Roles unverifiedRole = roleRepository.findByName(RoleType.UNVERIFIED)
                .orElseThrow(() -> new RuntimeException("Unverified role not found"));

        user.setRoles(Set.of(unverifiedRole));
        User savedUser = userRepository.save(user);
        return authMapper.toResponse(savedUser);
    }
}