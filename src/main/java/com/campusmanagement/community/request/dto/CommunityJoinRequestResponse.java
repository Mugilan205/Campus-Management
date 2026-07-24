package com.campusmanagement.community.request.dto;


import com.campusmanagement.community.request.enums.JoinRequestStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityJoinRequestResponse {

    private Long id;

    private Long communityId;

    private String communityName;

    private Long requestedById;

    private String requestedByName;

    private String message;

    private JoinRequestStatus status;

    private String reviewedBy;

    private String remarks;

    private LocalDateTime requestedAt;

    private LocalDateTime reviewedAt;

}