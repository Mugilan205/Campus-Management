package com.campusmanagement.verification.mapper;

import com.campusmanagement.verification.dto.VerificationRequest;
import com.campusmanagement.verification.dto.VerificationResponse;
import com.campusmanagement.verification.entity.Verification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VerificationMapper {

    Verification toEntity(VerificationRequest request);

    VerificationResponse toResponse(Verification verification);
}