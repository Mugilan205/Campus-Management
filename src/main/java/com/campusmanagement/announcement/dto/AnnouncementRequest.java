package com.campusmanagement.announcement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnnouncementRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;
}