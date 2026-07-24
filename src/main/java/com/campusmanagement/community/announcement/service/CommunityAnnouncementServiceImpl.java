package com.campusmanagement.community.announcement.service;

import com.campusmanagement.community.announcement.dto.CommunityAnnouncementRequest;
import com.campusmanagement.community.announcement.dto.CommunityAnnouncementResponse;
import com.campusmanagement.community.announcement.entity.CommunityAnnouncement;
import com.campusmanagement.community.announcement.mapper.CommunityAnnouncementMapper;
import com.campusmanagement.community.announcement.repository.CommunityAnnouncementRepository;
import com.campusmanagement.community.common.service.CommunityCommonService;
import com.campusmanagement.community.community.entity.Community;
import com.campusmanagement.community.permission.CommunityPermissionService;
import com.campusmanagement.security.SecurityUtils;
import com.campusmanagement.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommunityAnnouncementServiceImpl
        implements CommunityAnnouncementService {

    private final CommunityAnnouncementRepository announcementRepository;

    private final CommunityAnnouncementMapper mapper;

    private final CommunityPermissionService permissionService;

    private final CommunityCommonService commonService;

    @Override
    public CommunityAnnouncementResponse createAnnouncement(
            Long communityId,
            CommunityAnnouncementRequest request) {

        permissionService.checkModeratorPermission(communityId);

        Community community =
                commonService.getCommunity(communityId);

        User currentUser =
                SecurityUtils.getCurrentUser();

        CommunityAnnouncement announcement =
                CommunityAnnouncement.builder()
                        .community(community)
                        .createdBy(currentUser)
                        .title(request.getTitle())
                        .description(request.getDescription())
                        .build();

        return mapper.toResponse(
                announcementRepository.save(announcement));



    }

    @Override
    @Transactional(readOnly = true)
    public List<CommunityAnnouncementResponse> getAnnouncements(
            Long communityId) {

        permissionService.checkMemberPermission(communityId);

        Community community =
                commonService.getCommunity(communityId);

        return announcementRepository
                .findByCommunityOrderByCreatedAtDesc(community)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }



    @Override
    public void deleteAnnouncement(
            Long announcementId) {

        CommunityAnnouncement announcement =
                commonService.getAnnouncement(announcementId);

        permissionService.checkModeratorPermission(
                announcement.getCommunity().getId());

        announcementRepository.delete(announcement);
    }


}