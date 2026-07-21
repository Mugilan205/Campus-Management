package com.campusmanagement.user.controller;

import com.campusmanagement.verification.dto.VerificationDecisionRequest;
import com.campusmanagement.verification.dto.VerificationRequest;
import com.campusmanagement.verification.dto.VerificationResponse;
import com.campusmanagement.verification.service.VerificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/verification")
public class VerificationController {

    private final VerificationService verificationService;

    @PostMapping
    public ResponseEntity<VerificationResponse> submitVerification(
            @Valid @RequestBody VerificationRequest request) {

        VerificationResponse response =
                verificationService.submitVerification(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<VerificationResponse> getMyVerification() {

        return ResponseEntity.ok(
                verificationService.getMyVerification()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/pending")
    public ResponseEntity<List<VerificationResponse>> getPendingVerifications() {

        return ResponseEntity.ok(
                verificationService.getPendingVerifications()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{verificationId}/approve")
    public ResponseEntity<VerificationResponse> approveVerification(
            @PathVariable Long verificationId,
            @RequestBody VerificationDecisionRequest request) {

        return ResponseEntity.ok(
                verificationService.approveVerification(
                        verificationId,
                        request
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{verificationId}/reject")
    public ResponseEntity<VerificationResponse> rejectVerification(
            @PathVariable Long verificationId,
            @RequestBody VerificationDecisionRequest request) {

        return ResponseEntity.ok(
                verificationService.rejectVerification(
                        verificationId,
                        request
                )
        );
    }
}