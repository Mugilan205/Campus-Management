package com.campusmanagement.verification.service;


import com.campusmanagement.verification.dto.VerificationDecisionRequest;
import com.campusmanagement.verification.dto.VerificationRequest;
import com.campusmanagement.verification.dto.VerificationResponse;

import java.util.List;


public interface VerificationService {

    VerificationResponse submitVerification(VerificationRequest request);

    VerificationResponse getMyVerification();

    List<VerificationResponse> getPendingVerifications();

    VerificationResponse approveVerification(Long verificationId,
                                             VerificationDecisionRequest request);

    VerificationResponse rejectVerification(Long verificationId,
                                            VerificationDecisionRequest request);
}