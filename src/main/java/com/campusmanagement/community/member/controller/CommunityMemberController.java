package com.campusmanagement.community.member.controller;

import com.campusmanagement.community.member.dto.CommunityMemberResponse;
import com.campusmanagement.community.member.dto.UpdateCommunityMemberRoleRequest;
import com.campusmanagement.community.member.service.CommunityMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communities/{communityId}/members")
@RequiredArgsConstructor
public class CommunityMemberController {

    private final CommunityMemberService service;

    @GetMapping
    public ResponseEntity<List<CommunityMemberResponse>>
    getMembers(
            @PathVariable Long communityId) {

        return ResponseEntity.ok(
                service.getMembers(communityId));
    }

    @PutMapping("/{userId}/role")
    public ResponseEntity<CommunityMemberResponse>
    updateRole(
            @PathVariable Long communityId,
            @PathVariable Long userId,
            @Valid @RequestBody UpdateCommunityMemberRoleRequest request) {

        return ResponseEntity.ok(
                service.updateMemberRole(
                        communityId,
                        userId,
                        request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> removeMember(
            @PathVariable Long communityId,
            @PathVariable Long userId) {

        service.removeMember(
                communityId,
                userId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/leave")
    public ResponseEntity<Void> leaveCommunity(
            @PathVariable Long communityId) {

        service.leaveCommunity(communityId);

        return ResponseEntity.noContent().build();
    }

}
