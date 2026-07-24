package com.campusmanagement.community.common.service;

import com.campusmanagement.community.announcement.entity.CommunityAnnouncement;
import com.campusmanagement.community.community.entity.Community;
import com.campusmanagement.community.member.entity.CommunityMember;
import com.campusmanagement.community.request.entity.CommunityJoinRequest;

public interface CommunityCommonService {

    Community getCommunity(Long communityId);

    CommunityAnnouncement getAnnouncement(Long announcementId);

    CommunityJoinRequest getJoinRequest(Long requestId);

    CommunityMember getMembership(Long communityId, Long userId);

}
