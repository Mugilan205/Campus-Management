package com.campusmanagement.community.announcement.service;

import com.campusmanagement.community.announcement.dto.CommunityAnnouncementRequest;
import com.campusmanagement.community.announcement.dto.CommunityAnnouncementResponse;

import java.util.List;

public interface CommunityAnnouncementService {

    CommunityAnnouncementResponse createAnnouncement(
            Long communityId,
            CommunityAnnouncementRequest request
    );

    List<CommunityAnnouncementResponse> getAnnouncements(
            Long communityId
    );

    void deleteAnnouncement(Long announcementId);

}