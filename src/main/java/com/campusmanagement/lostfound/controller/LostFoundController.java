package com.campusmanagement.lostfound.controller;

import com.campusmanagement.lostfound.dto.ApprovalDecisionRequest;
import com.campusmanagement.lostfound.dto.ClaimRequest;
import com.campusmanagement.lostfound.dto.LostFoundRequest;
import com.campusmanagement.lostfound.dto.LostFoundResponse;
import com.campusmanagement.lostfound.service.LostFoundService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lost-found")
@RequiredArgsConstructor
public class LostFoundController {

    private final LostFoundService lostFoundService;

    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<LostFoundResponse> createPost(
            @Valid @RequestBody LostFoundRequest request) {

        return ResponseEntity.ok(
                lostFoundService.createPost(request));
    }

    @GetMapping
    public ResponseEntity<List<LostFoundResponse>> getActivePosts() {

        return ResponseEntity.ok(
                lostFoundService.getActivePosts());
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<LostFoundResponse>> getMyPosts() {

        return ResponseEntity.ok(
                lostFoundService.getMyPosts());
    }

    @GetMapping("/pending")
    @PreAuthorize("hasRole('FACULTY')")
    public ResponseEntity<List<LostFoundResponse>> getPendingPosts() {

        return ResponseEntity.ok(
                lostFoundService.getPendingPosts());
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasRole('FACULTY')")
    public ResponseEntity<LostFoundResponse> approve(
            @PathVariable Long id,
            @RequestBody ApprovalDecisionRequest request) {

        return ResponseEntity.ok(
                lostFoundService.approvePost(id, request));
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasRole('FACULTY')")
    public ResponseEntity<LostFoundResponse> reject(
            @PathVariable Long id,
            @RequestBody ApprovalDecisionRequest request) {

        return ResponseEntity.ok(
                lostFoundService.rejectPost(id, request));
    }

    @PutMapping("/{id}/claim")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<LostFoundResponse> claim(
            @PathVariable Long id,
            @RequestBody ClaimRequest request) {

        return ResponseEntity.ok(
                lostFoundService.claimItem(id, request));
    }

    @PutMapping("/{id}/confirm")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<LostFoundResponse> confirm(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                lostFoundService.confirmReturn(id));
    }
}