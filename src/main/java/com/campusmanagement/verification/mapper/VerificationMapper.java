package com.campusmanagement.verification.mapper;

import com.campusmanagement.verification.dto.VerificationRequest;
import com.campusmanagement.verification.dto.VerificationResponse;
import com.campusmanagement.verification.entity.Verification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VerificationMapper {


    Verification toEntity(VerificationRequest request);

    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "email", source = "user.email")
    VerificationResponse toResponse(Verification verification);
}