package com.campusmanagement.lostfound.enitity;

import com.campusmanagement.lostfound.enums.ItemCategory;
import com.campusmanagement.lostfound.enums.LostFoundStatus;
import com.campusmanagement.lostfound.enums.LostFoundType;
import com.campusmanagement.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "lost_found")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LostFound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LostFoundType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemCategory category;

    @Column(columnDefinition = "TEXT")
    private String attachmentUrl;

    @Column(nullable = false)
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by")
    private User approvedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "claimed_by")
    private User claimedBy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LostFoundStatus status;

    @Column(columnDefinition = "TEXT")
    private String claimMessage;

    @Column(columnDefinition = "TEXT")
    private String remarks;

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime approvedAt;

    private LocalDateTime claimedAt;

    private LocalDateTime returnedAt;
}