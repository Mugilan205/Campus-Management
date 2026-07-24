package com.campusmanagement.community.announcement.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityAnnouncementResponse {

    private Long id;

    private Long communityId;

    private String communityName;

    private String title;

    private String description;

    private String createdBy;

    private LocalDateTime createdAt;
}
