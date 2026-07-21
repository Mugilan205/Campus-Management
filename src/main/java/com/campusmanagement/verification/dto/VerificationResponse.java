package com.campusmanagement.verification.dto;

import com.campusmanagement.verification.enums.RequestedRole;
import com.campusmanagement.verification.enums.VerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationResponse {

    private Long id;

    private String userName;

    private String email;

    private RequestedRole requestedRole;

    private VerificationStatus status;

    private String documentUrl;

    private String remarks;

    private LocalDateTime submittedAt;

    private LocalDateTime reviewedAt;
}