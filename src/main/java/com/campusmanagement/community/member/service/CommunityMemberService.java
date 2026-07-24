package com.campusmanagement.community.member.service;

import com.campusmanagement.community.member.dto.CommunityMemberResponse;
import com.campusmanagement.community.member.dto.UpdateCommunityMemberRoleRequest;

import java.util.List;

public interface CommunityMemberService {

    List<CommunityMemberResponse> getMembers(
            Long communityId);

    CommunityMemberResponse updateMemberRole(
            Long communityId,
            Long userId,
            UpdateCommunityMemberRoleRequest request);

    void removeMember(
            Long communityId,
            Long userId);

    void leaveCommunity(
            Long communityId);

}