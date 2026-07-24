package com.campusmanagement.community.announcement.controller;

import com.campusmanagement.community.announcement.dto.CommunityAnnouncementRequest;
import com.campusmanagement.community.announcement.dto.CommunityAnnouncementResponse;
import com.campusmanagement.community.announcement.service.CommunityAnnouncementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/community-announcements")
@RequiredArgsConstructor
public class CommunityAnnouncementController {

    private final CommunityAnnouncementService service;

    @PostMapping("/{communityId}")
    public ResponseEntity<CommunityAnnouncementResponse>
    createAnnouncement(
            @PathVariable Long communityId,
            @Valid @RequestBody CommunityAnnouncementRequest request){

        return ResponseEntity.ok(
                service.createAnnouncement(
                        communityId,
                        request));
    }

    @GetMapping("/{communityId}")
    public ResponseEntity<List<CommunityAnnouncementResponse>>
    getAnnouncements(
            @PathVariable Long communityId){

        return ResponseEntity.ok(
                service.getAnnouncements(
                        communityId));
    }

    @DeleteMapping("/{announcementId}")
    public ResponseEntity<Void> deleteAnnouncement(
            @PathVariable Long announcementId){

        service.deleteAnnouncement(announcementId);

        return ResponseEntity.noContent().build();
    }

}
