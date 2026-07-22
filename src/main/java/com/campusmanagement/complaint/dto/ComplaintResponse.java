package com.campusmanagement.complaint.dto;

import com.campusmanagement.complaint.enums.ComplaintCategory;
import com.campusmanagement.complaint.enums.ComplaintStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintResponse {

    private Long id;

    private String subject;

    private String description;

    private ComplaintCategory category;

    private String attachmentUrl;

    private ComplaintStatus status;

    private String createdBy;

    private String resolvedBy;

    private String remarks;

    private LocalDateTime createdAt;

    private LocalDateTime resolvedAt;
}