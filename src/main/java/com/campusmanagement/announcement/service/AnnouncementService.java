package com.campusmanagement.announcement.service;

import com.campusmanagement.announcement.dto.AnnouncementDecisionRequest;
import com.campusmanagement.announcement.dto.AnnouncementRequest;
import com.campusmanagement.announcement.dto.AnnouncementResponse;

import java.util.List;

public interface AnnouncementService {

    AnnouncementResponse createAnnouncement(AnnouncementRequest request);

    List<AnnouncementResponse> getMyAnnouncements();

    List<AnnouncementResponse> getAllApprovedAnnouncements();

    List<AnnouncementResponse> getPendingAnnouncements();

    AnnouncementResponse approveAnnouncement(
            Long id,
            AnnouncementDecisionRequest request
    );

    AnnouncementResponse rejectAnnouncement(
            Long id,
            AnnouncementDecisionRequest request
    );
}