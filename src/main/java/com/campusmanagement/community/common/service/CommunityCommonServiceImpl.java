package com.campusmanagement.community.common.service;

import com.campusmanagement.community.announcement.entity.CommunityAnnouncement;
import com.campusmanagement.community.announcement.repository.CommunityAnnouncementRepository;
import com.campusmanagement.community.community.entity.Community;
import com.campusmanagement.community.community.repository.CommunityRepository;
import com.campusmanagement.community.member.entity.CommunityMember;
import com.campusmanagement.community.member.repository.CommunityMemberRepository;
import com.campusmanagement.community.request.entity.CommunityJoinRequest;
import com.campusmanagement.community.request.repository.CommunityJoinRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityCommonServiceImpl
        implements CommunityCommonService {

    private final CommunityRepository communityRepository;

    private final CommunityAnnouncementRepository announcementRepository;

    private final CommunityJoinRequestRepository joinRequestRepository;

    private final CommunityMemberRepository memberRepository;

    @Override
    public Community getCommunity(Long communityId) {

        return communityRepository.findById(communityId)
                .orElseThrow(() ->
                        new RuntimeException("Community not found."));
    }

    @Override
    public CommunityAnnouncement getAnnouncement(Long announcementId) {

        return announcementRepository.findById(announcementId)
                .orElseThrow(() ->
                        new RuntimeException("Announcement not found."));
    }

    @Override
    public CommunityJoinRequest getJoinRequest(Long requestId) {

        return joinRequestRepository.findById(requestId)
                .orElseThrow(() ->
                        new RuntimeException("Join request not found."));
    }

    @Override
    public CommunityMember getMembership(
            Long communityId,
            Long userId) {

        Community community = getCommunity(communityId);

        return memberRepository
                .findByCommunityIdAndUserId(
                        community.getId(),
                        userId)
                .orElseThrow(() ->
                        new RuntimeException("Membership not found."));
    }
}

