package com.campusmanagement.announcement.dto;

import com.campusmanagement.announcement.enums.AnnouncementStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementResponse {

    private Long id;

    private String title;

    private String description;

    private String createdBy;

    private String approvedBy;

    private AnnouncementStatus status;

    private String remarks;

    private LocalDateTime createdAt;

    private LocalDateTime approvedAt;

}