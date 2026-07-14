package com.campusmanagement.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String name;

    private String email;

    private List<String> roles;

}