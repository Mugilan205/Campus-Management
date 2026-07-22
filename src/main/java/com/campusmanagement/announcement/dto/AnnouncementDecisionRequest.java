package com.campusmanagement.announcement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnnouncementDecisionRequest {

    @NotBlank
    private String remarks;
}