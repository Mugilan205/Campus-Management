package com.campusmanagement.announcement.service;

import com.campusmanagement.announcement.dto.AnnouncementDecisionRequest;
import com.campusmanagement.announcement.dto.AnnouncementRequest;
import com.campusmanagement.announcement.dto.AnnouncementResponse;
import com.campusmanagement.announcement.entity.Announcement;
import com.campusmanagement.announcement.enums.AnnouncementStatus;
import com.campusmanagement.announcement.mapper.AnnouncementMapper;
import com.campusmanagement.announcement.repository.AnnouncementRepository;
import com.campusmanagement.security.SecurityUtils;
import com.campusmanagement.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    private final AnnouncementMapper announcementMapper;

    @Override
    public AnnouncementResponse createAnnouncement(
            AnnouncementRequest request) {

        User currentUser = SecurityUtils.getCurrentUser();

        Announcement announcement = Announcement.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .createdBy(currentUser)
                .status(AnnouncementStatus.PENDING)
                .build();

        Announcement savedAnnouncement =
                announcementRepository.save(announcement);

        return announcementMapper.toResponse(savedAnnouncement);
    }

    @Override
    public List<AnnouncementResponse> getMyAnnouncements() {

        User currentUser = SecurityUtils.getCurrentUser();

        return announcementRepository.findByCreatedBy(currentUser)
                .stream()
                .map(announcementMapper::toResponse)
                .toList();
    }

    @Override
    public List<AnnouncementResponse> getAllApprovedAnnouncements() {

        return announcementRepository
                .findAllByStatus(AnnouncementStatus.APPROVED)
                .stream()
                .map(announcementMapper::toResponse)
                .toList();
    }

    @Override
    public List<AnnouncementResponse> getPendingAnnouncements() {

        return announcementRepository
                .findAllByStatus(AnnouncementStatus.PENDING)
                .stream()
                .map(announcementMapper::toResponse)
                .toList();
    }

    @Override
    public AnnouncementResponse approveAnnouncement(
            Long id,
            AnnouncementDecisionRequest request) {

        Announcement announcement =
                announcementRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Announcement not found."));

        if (announcement.getStatus() != AnnouncementStatus.PENDING) {
            throw new RuntimeException(
                    "Announcement has already been reviewed.");
        }

        announcement.setStatus(AnnouncementStatus.APPROVED);
        announcement.setApprovedBy(SecurityUtils.getCurrentUser());
        announcement.setApprovedAt(LocalDateTime.now());
        announcement.setRemarks(request.getRemarks());

        Announcement savedAnnouncement =
                announcementRepository.save(announcement);

        return announcementMapper.toResponse(savedAnnouncement);
    }

    @Override
    public AnnouncementResponse rejectAnnouncement(
            Long id,
            AnnouncementDecisionRequest request) {

        Announcement announcement =
                announcementRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Announcement not found."));

        if (announcement.getStatus() != AnnouncementStatus.PENDING) {
            throw new RuntimeException(
                    "Announcement has already been reviewed.");
        }

        announcement.setStatus(AnnouncementStatus.REJECTED);
        announcement.setApprovedBy(SecurityUtils.getCurrentUser());
        announcement.setApprovedAt(LocalDateTime.now());
        announcement.setRemarks(request.getRemarks());

        Announcement savedAnnouncement =
                announcementRepository.save(announcement);

        return announcementMapper.toResponse(savedAnnouncement);
    }

}