package com.campusmanagement.community.community.dto;

import com.campusmanagement.community.community.enums.CommunityVisibility;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityUpdateRequest {

    private String description;

    private String iconUrl;

    private String bannerUrl;

    private CommunityVisibility visibility;
}
