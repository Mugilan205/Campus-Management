package com.campusmanagement.community.community.controller;

import com.campusmanagement.community.community.dto.CommunityRequest;
import com.campusmanagement.community.community.dto.CommunityResponse;
import com.campusmanagement.community.community.dto.CommunityUpdateRequest;
import com.campusmanagement.community.community.service.CommunityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communities")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping
    @PreAuthorize("hasAnyRole('FACULTY','ADMIN')")
    public ResponseEntity<CommunityResponse> createCommunity(
            @Valid @RequestBody CommunityRequest request) {

        return ResponseEntity.ok(
                communityService.createCommunity(request));
    }

    @GetMapping
    public ResponseEntity<List<CommunityResponse>> getAllCommunities() {

        return ResponseEntity.ok(
                communityService.getAllCommunities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunityResponse> getCommunity(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                communityService.getCommunity(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('FACULTY','ADMIN')")
    public ResponseEntity<CommunityResponse> updateCommunity(
            @PathVariable Long id,
            @RequestBody CommunityUpdateRequest request) {

        return ResponseEntity.ok(
                communityService.updateCommunity(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCommunity(
            @PathVariable Long id) {

        communityService.deleteCommunity(id);

        return ResponseEntity.noContent().build();
    }
}