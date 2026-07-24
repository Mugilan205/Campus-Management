package com.campusmanagement.community.request.service;

import com.campusmanagement.community.community.entity.Community;
import com.campusmanagement.community.community.repository.CommunityRepository;
import com.campusmanagement.community.member.entity.CommunityMember;
import com.campusmanagement.community.member.enums.CommunityMemberRole;
import com.campusmanagement.community.member.repository.CommunityMemberRepository;
import com.campusmanagement.community.permission.CommunityPermissionService;
import com.campusmanagement.community.request.dto.CommunityJoinRequestDecisionRequest;
import com.campusmanagement.community.request.dto.CommunityJoinRequestRequest;
import com.campusmanagement.community.request.dto.CommunityJoinRequestResponse;
import com.campusmanagement.community.request.entity.CommunityJoinRequest;
import com.campusmanagement.community.request.enums.JoinRequestStatus;
import com.campusmanagement.community.request.mapper.CommunityJoinRequestMapper;
import com.campusmanagement.community.request.repository.CommunityJoinRequestRepository;
import com.campusmanagement.security.SecurityUtils;
import com.campusmanagement.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommunityJoinRequestServiceImpl implements CommunityJoinRequestService {

    private final CommunityRepository communityRepository;

    private final CommunityJoinRequestRepository joinRequestRepository;

    private final CommunityMemberRepository memberRepository;

    private final CommunityJoinRequestMapper mapper;

    private final CommunityPermissionService permissionService;

    @Override
    public CommunityJoinRequestResponse requestToJoin(
            Long communityId,
            CommunityJoinRequestRequest request) {

        Community community = communityRepository.findById(communityId)
                .orElseThrow(() ->
                        new RuntimeException("Community not found."));

        User currentUser = SecurityUtils.getCurrentUser();

        if (memberRepository.existsByCommunityAndUser(community, currentUser)) {
            throw new RuntimeException("You are already a member.");
        }

        if (joinRequestRepository.existsByCommunityAndRequestedByAndStatus(
                community,
                currentUser,
                JoinRequestStatus.PENDING)) {

            throw new RuntimeException("You already have a pending request.");
        }

        CommunityJoinRequest joinRequest = CommunityJoinRequest.builder()
                .community(community)
                .requestedBy(currentUser)
                .message(request.getMessage())
                .status(JoinRequestStatus.PENDING)
                .build();

        return mapper.toResponse(
                joinRequestRepository.save(joinRequest));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommunityJoinRequestResponse> getMyRequests() {

        User currentUser = SecurityUtils.getCurrentUser();

        return joinRequestRepository.findByRequestedBy(currentUser)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommunityJoinRequestResponse> getPendingRequests(
            Long communityId) {

        permissionService.checkModeratorPermission(communityId);

        Community community = communityRepository.findById(communityId)
                .orElseThrow(() ->
                        new RuntimeException("Community not found."));

        return joinRequestRepository
                .findByCommunityAndStatus(
                        community,
                        JoinRequestStatus.PENDING)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public CommunityJoinRequestResponse reviewRequest(
            Long requestId,
            CommunityJoinRequestDecisionRequest request) {

        CommunityJoinRequest joinRequest = joinRequestRepository.findById(requestId)
                .orElseThrow(() ->
                        new RuntimeException("Join request not found."));

        permissionService.checkModeratorPermission(
                joinRequest.getCommunity().getId());

        if (joinRequest.getStatus() != JoinRequestStatus.PENDING) {
            throw new RuntimeException("Request has already been reviewed.");
        }

        User reviewer = SecurityUtils.getCurrentUser();

        joinRequest.setReviewedBy(reviewer);
        joinRequest.setReviewedAt(LocalDateTime.now());
        joinRequest.setRemarks(request.getRemarks());

        if (Boolean.TRUE.equals(request.getApproved())) {

            joinRequest.setStatus(JoinRequestStatus.APPROVED);

            CommunityMember member = CommunityMember.builder()
                    .community(joinRequest.getCommunity())
                    .user(joinRequest.getRequestedBy())
                    .role(CommunityMemberRole.MEMBER)
                    .build();

            memberRepository.save(member);

        } else {

            joinRequest.setStatus(JoinRequestStatus.REJECTED);
        }

        return mapper.toResponse(
                joinRequestRepository.save(joinRequest));
    }
}