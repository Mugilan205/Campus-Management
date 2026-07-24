package com.campusmanagement.community.announcement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityAnnouncementRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

}