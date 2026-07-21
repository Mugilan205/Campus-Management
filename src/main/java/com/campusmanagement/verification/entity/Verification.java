package com.campusmanagement.verification.entity;

import com.campusmanagement.user.entity.User;
import com.campusmanagement.verification.enums.RequestedRole;
import com.campusmanagement.verification.enums.VerificationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "verifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Verification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestedRole requestedRole;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VerificationStatus status;

    @Column(nullable = false)
    private String documentUrl;

    private String remarks;

    private LocalDateTime submittedAt;

    private LocalDateTime reviewedAt;
}