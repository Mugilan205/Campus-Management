package com.campusmanagement.announcement.controller;

import com.campusmanagement.announcement.dto.AnnouncementDecisionRequest;
import com.campusmanagement.announcement.dto.AnnouncementRequest;
import com.campusmanagement.announcement.dto.AnnouncementResponse;
import com.campusmanagement.announcement.service.AnnouncementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/announcements")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<AnnouncementResponse> createAnnouncement(
            @Valid @RequestBody AnnouncementRequest request) {

        return ResponseEntity.ok(
                announcementService.createAnnouncement(request));
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<AnnouncementResponse>> getMyAnnouncements() {

        return ResponseEntity.ok(
                announcementService.getMyAnnouncements());
    }

    @GetMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<AnnouncementResponse>>
    getApprovedAnnouncements() {

        return ResponseEntity.ok(
                announcementService.getAllApprovedAnnouncements());
    }

    @GetMapping("/pending")
    @PreAuthorize("hasRole('FACULTY')")
    public ResponseEntity<List<AnnouncementResponse>>
    getPendingAnnouncements() {

        return ResponseEntity.ok(
                announcementService.getPendingAnnouncements());
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasRole('FACULTY')")
    public ResponseEntity<AnnouncementResponse> approveAnnouncement(
            @PathVariable Long id,
            @Valid @RequestBody AnnouncementDecisionRequest request) {

        return ResponseEntity.ok(
                announcementService.approveAnnouncement(id, request));
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasRole('FACULTY')")
    public ResponseEntity<AnnouncementResponse> rejectAnnouncement(
            @PathVariable Long id,
            @Valid @RequestBody AnnouncementDecisionRequest request) {

        return ResponseEntity.ok(
                announcementService.rejectAnnouncement(id, request));
    }
}