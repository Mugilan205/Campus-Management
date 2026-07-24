package com.campusmanagement.community.member.dto;

import com.campusmanagement.community.member.enums.CommunityMemberRole;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityMemberResponse {

    private Long userId;

    private String name;

    private String email;

    private CommunityMemberRole role;

    private LocalDateTime joinedAt;
}