package com.campusmanagement.community.member.dto;

import com.campusmanagement.community.member.enums.CommunityMemberRole;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCommunityMemberRoleRequest {

    @NotNull
    private CommunityMemberRole role;

}