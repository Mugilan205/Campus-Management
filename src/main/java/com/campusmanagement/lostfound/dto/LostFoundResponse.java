package com.campusmanagement.lostfound.dto;

import com.campusmanagement.lostfound.enums.ItemCategory;
import com.campusmanagement.lostfound.enums.LostFoundStatus;
import com.campusmanagement.lostfound.enums.LostFoundType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LostFoundResponse {

    private Long id;

    private String title;

    private String description;

    private LostFoundType type;

    private ItemCategory category;

    private String attachmentUrl;

    private String location;

    private LostFoundStatus status;

    private String createdBy;

    private String approvedBy;

    private String claimedBy;

    private String claimMessage;

    private String remarks;

    private LocalDateTime createdAt;

    private LocalDateTime approvedAt;

    private LocalDateTime claimedAt;

    private LocalDateTime returnedAt;
}