package com.campusmanagement.lostfound.service;

import com.campusmanagement.lostfound.dto.ApprovalDecisionRequest;
import com.campusmanagement.lostfound.dto.ClaimRequest;
import com.campusmanagement.lostfound.dto.LostFoundRequest;
import com.campusmanagement.lostfound.dto.LostFoundResponse;

import java.util.List;

public interface LostFoundService {

    LostFoundResponse createPost(LostFoundRequest request);

    List<LostFoundResponse> getMyPosts();

    List<LostFoundResponse> getActivePosts();

    List<LostFoundResponse> getPendingPosts();

    LostFoundResponse approvePost(
            Long id,
            ApprovalDecisionRequest request
    );

    LostFoundResponse rejectPost(
            Long id,
            ApprovalDecisionRequest request
    );

    LostFoundResponse submitClaim(
            Long id,
            ClaimRequest request
    );

    LostFoundResponse acceptClaim(Long id);

    LostFoundResponse rejectClaim(
            Long id,
            ApprovalDecisionRequest request
    );

    LostFoundResponse confirmReturn(Long id);

    LostFoundResponse claimItem(
            Long id,
            ClaimRequest request);
}