package com.campusmanagement.community.community.service;


import com.campusmanagement.community.community.dto.CommunityRequest;
import com.campusmanagement.community.community.dto.CommunityUpdateRequest;
import com.campusmanagement.community.community.dto.CommunityResponse;

import java.util.List;

public interface CommunityService {

    CommunityResponse createCommunity(
            CommunityRequest request);

    List<CommunityResponse> getAllCommunities();

    CommunityResponse getCommunity(Long id);

    CommunityResponse updateCommunity(
            Long id,
            CommunityUpdateRequest request);

    void deleteCommunity(Long id);

}
