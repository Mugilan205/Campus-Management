package com.campusmanagement.community.request.service;

import com.campusmanagement.community.request.dto.CommunityJoinRequestDecisionRequest;
import com.campusmanagement.community.request.dto.CommunityJoinRequestRequest;
import com.campusmanagement.community.request.dto.CommunityJoinRequestResponse;

import java.util.List;

public interface CommunityJoinRequestService {

    CommunityJoinRequestResponse requestToJoin(
            Long communityId,
            CommunityJoinRequestRequest request);

    List<CommunityJoinRequestResponse> getMyRequests();

    List<CommunityJoinRequestResponse> getPendingRequests(
            Long communityId);

    CommunityJoinRequestResponse reviewRequest(
            Long requestId,
            CommunityJoinRequestDecisionRequest request);

}