package com.campusmanagement.verification.repository;

import com.campusmanagement.user.entity.User;
import com.campusmanagement.verification.entity.Verification;
import com.campusmanagement.verification.enums.VerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VerificationRepository extends JpaRepository<Verification, Long> {

    Optional<Verification> findByUserAndStatus(User user, VerificationStatus status);

    List<Verification> findAllByStatus(VerificationStatus status);

    Optional<Verification>

    findFirstByUserOrderBySubmittedAtDesc(User user);
}