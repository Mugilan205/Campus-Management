package com.campusmanagement.community.member.entity;

import com.campusmanagement.community.community.entity.Community;
import com.campusmanagement.community.member.enums.CommunityMemberRole;
import com.campusmanagement.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "community_members",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "community_id",
                                "user_id"
                        }
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "community_id",
            nullable = false
    )
    private Community community;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommunityMemberRole role;

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime joinedAt = LocalDateTime.now();
}