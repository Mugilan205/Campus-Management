package com.campusmanagement.auth.dto.response;

import com.campusmanagement.common.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class RegisterResponse {

    private Long id;
    private String name;
    private String email;
    private Set<RoleType> roles;
}