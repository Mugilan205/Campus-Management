package com.campusmanagement.community.request.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityJoinRequestRequest {

    @Size(max = 500)
    private String message;

}