package com.campusmanagement.auth.mapper;

import com.campusmanagement.auth.dto.request.RegisterRequest;
import com.campusmanagement.auth.dto.response.RegisterResponse;
import com.campusmanagement.user.entity.Roles;
import com.campusmanagement.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    User toEntity(RegisterRequest request);
    RegisterResponse toResponse(User user);
    default String map(Roles role) { // this method is for mapping Roles entity to String in RegisterResponse
        return role.getName().name();//returns string
    }
}