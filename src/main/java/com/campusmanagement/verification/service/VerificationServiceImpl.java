package com.campusmanagement.verification.service;

import com.campusmanagement.common.enums.RoleType;
import com.campusmanagement.security.SecurityUtils;
import com.campusmanagement.user.entity.Roles;
import com.campusmanagement.user.entity.User;
import com.campusmanagement.user.repository.RoleRepository;
import com.campusmanagement.user.repository.UserRepository;
import com.campusmanagement.verification.dto.VerificationDecisionRequest;
import com.campusmanagement.verification.dto.VerificationRequest;
import com.campusmanagement.verification.dto.VerificationResponse;
import com.campusmanagement.verification.entity.Verification;
import com.campusmanagement.verification.enums.VerificationStatus;
import com.campusmanagement.verification.mapper.VerificationMapper;
import com.campusmanagement.verification.repository.VerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public abstract class VerificationServiceImpl implements VerificationService {

    private final VerificationRepository verificationRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final VerificationMapper verificationMapper;


    @Override
    public VerificationResponse submitVerification(
            VerificationRequest request) {

        User currentUser = SecurityUtils.getCurrentUser();

        verificationRepository
                .findByUserAndStatus(
                        currentUser,
                        VerificationStatus.PENDING)
                .ifPresent(v -> {
                    throw new RuntimeException(
                            "You already have a pending verification request.");
                });

        Verification verification =
                verificationMapper.toEntity(request);

        verification.setUser(currentUser);
        verification.setStatus(VerificationStatus.PENDING);
        verification.setSubmittedAt(LocalDateTime.now());

        verification =
                verificationRepository.save(verification);

        return verificationMapper.toResponse(verification);
    }


    @Override
    public VerificationResponse getMyVerification() {

        User currentUser = SecurityUtils.getCurrentUser();

        Verification verification =
                verificationRepository
                        .findFirstByUserOrderBySubmittedAtDesc(currentUser)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "No verification found."));

        return verificationMapper.toResponse(verification);
    }

    @Override
    public VerificationResponse approveVerification(
            Long verificationId,
            VerificationDecisionRequest request) {

        Verification verification = verificationRepository.findById(verificationId)
                .orElseThrow(() ->
                        new RuntimeException("Verification not found"));

        if (verification.getStatus() != VerificationStatus.PENDING) {
            throw new RuntimeException(
                    "Verification request has already been processed.");
        }

        User user = verification.getUser();

        user.getRoles().removeIf(role ->
                role.getName() == RoleType.UNVERIFIED);

        Roles requestedRole = roleRepository.findByName(
                        RoleType.valueOf(verification.getRequestedRole().name()))
                .orElseThrow(() ->
                        new RuntimeException("Role not found"));

        user.getRoles().add(requestedRole);

        userRepository.save(user);

        verification.setStatus(VerificationStatus.APPROVED);
        verification.setRemarks(request.getRemarks());
        verification.setReviewedAt(LocalDateTime.now());

        verificationRepository.save(verification);

        return verificationMapper.toResponse(verification);
    }
}