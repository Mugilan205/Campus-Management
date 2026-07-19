package com.campusmanagement.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
@Getter
@AllArgsConstructor
public class LoginResponse {

    private String accessToken;

    private String tokenType;

    private Long expiresIn;

    private UserResponse user;

}