package com.campusmanagement.community.community.dto;

import com.campusmanagement.community.community.enums.CommunityVisibility;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private String iconUrl;

    private String bannerUrl;

    @NotNull
    private CommunityVisibility visibility;
}