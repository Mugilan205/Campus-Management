package com.campusmanagement.community.community.dto;

import com.campusmanagement.community.community.enums.CommunityVisibility;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityResponse {

    private Long id;

    private String name;

    private String description;

    private String iconUrl;

    private String bannerUrl;

    private CommunityVisibility visibility;

    private String createdBy;

    private LocalDateTime createdAt;
}