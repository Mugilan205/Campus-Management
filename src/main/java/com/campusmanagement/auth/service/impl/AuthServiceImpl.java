package com.campusmanagement.auth.service.impl;

import com.campusmanagement.auth.dto.request.LoginRequest;
import com.campusmanagement.auth.dto.request.RegisterRequest;
import com.campusmanagement.auth.dto.response.LoginResponse;
import com.campusmanagement.auth.dto.response.RegisterResponse;
import com.campusmanagement.auth.mapper.AuthMapper;
import com.campusmanagement.auth.service.AuthService;
import com.campusmanagement.common.enums.RoleType;
import com.campusmanagement.security.CustomUserDetails;
import com.campusmanagement.user.entity.Roles;
import com.campusmanagement.security.JwtService;
import com.campusmanagement.user.entity.User;
import com.campusmanagement.user.repository.RoleRepository;
import com.campusmanagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
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

    @Override
    public LoginResponse login(LoginRequest request) {

        System.out.println("came to impl");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User user = userDetails.getUser();

        String token = jwtService.generateToken(user);

        return LoginResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(36000L)
                .user(authMapper.toUserResponse(user))
                .build();
    }
}
