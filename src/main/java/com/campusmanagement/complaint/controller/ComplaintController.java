package com.campusmanagement.complaint.controller;

import com.campusmanagement.complaint.dto.ComplaintDecisionRequest;
import com.campusmanagement.complaint.dto.ComplaintRequest;
import com.campusmanagement.complaint.dto.ComplaintResponse;
import com.campusmanagement.complaint.enums.ComplaintStatus;
import com.campusmanagement.complaint.service.ComplaintService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;

    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ComplaintResponse> createComplaint(
            @Valid @RequestBody ComplaintRequest request) {

        return ResponseEntity.ok(
                complaintService.createComplaint(request));
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<ComplaintResponse>>
    getMyComplaints() {

        return ResponseEntity.ok(
                complaintService.getMyComplaints());
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('FACULTY','ADMIN')")
    public ResponseEntity<List<ComplaintResponse>>
    getAllComplaints() {

        return ResponseEntity.ok(
                complaintService.getAllComplaints());
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyRole('FACULTY','ADMIN')")
    public ResponseEntity<List<ComplaintResponse>>
    getComplaintsByStatus(
            @PathVariable ComplaintStatus status) {

        return ResponseEntity.ok(
                complaintService.getComplaintsByStatus(status));
    }

    @PutMapping("/{id}/progress")
    @PreAuthorize("hasAnyRole('FACULTY','ADMIN')")
    public ResponseEntity<ComplaintResponse> markInProgress(
            @PathVariable Long id,
            @RequestBody ComplaintDecisionRequest request) {

        return ResponseEntity.ok(
                complaintService.markInProgress(id, request));
    }

    @PutMapping("/{id}/resolve")
    @PreAuthorize("hasAnyRole('FACULTY','ADMIN')")
    public ResponseEntity<ComplaintResponse> resolveComplaint(
            @PathVariable Long id,
            @RequestBody ComplaintDecisionRequest request) {

        return ResponseEntity.ok(
                complaintService.resolveComplaint(id, request));
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasAnyRole('FACULTY','ADMIN')")
    public ResponseEntity<ComplaintResponse> rejectComplaint(
            @PathVariable Long id,
            @RequestBody ComplaintDecisionRequest request) {

        return ResponseEntity.ok(
                complaintService.rejectComplaint(id, request));
    }
}
