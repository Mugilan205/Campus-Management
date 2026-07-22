package com.campusmanagement.complaint.service;

import com.campusmanagement.complaint.dto.ComplaintDecisionRequest;
import com.campusmanagement.complaint.dto.ComplaintRequest;
import com.campusmanagement.complaint.dto.ComplaintResponse;
import com.campusmanagement.complaint.entity.Complaint;
import com.campusmanagement.complaint.enums.ComplaintStatus;
import com.campusmanagement.complaint.mapper.ComplaintMapper;
import com.campusmanagement.complaint.repository.ComplaintRepository;
import com.campusmanagement.security.SecurityUtils;
import com.campusmanagement.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    private final ComplaintMapper complaintMapper;

    @Override
    public ComplaintResponse createComplaint(
            ComplaintRequest request) {

        User currentUser = SecurityUtils.getCurrentUser();

        Complaint complaint = Complaint.builder()
                .subject(request.getSubject())
                .description(request.getDescription())
                .category(request.getCategory())
                .priority(request.getPriority())
                .attachmentUrl(request.getAttachmentUrl())
                .createdBy(currentUser)
                .status(ComplaintStatus.OPEN)
                .build();

        Complaint savedComplaint =
                complaintRepository.save(complaint);

        return complaintMapper.toResponse(savedComplaint);
    }

    @Override
    public List<ComplaintResponse> getMyComplaints() {

        User currentUser = SecurityUtils.getCurrentUser();

        return complaintRepository
                .findByCreatedBy(currentUser)
                .stream()
                .map(complaintMapper::toResponse)
                .toList();
    }

    @Override
    public List<ComplaintResponse> getAllComplaints() {

        return complaintRepository.findAll()
                .stream()
                .map(complaintMapper::toResponse)
                .toList();
    }

    @Override
    public List<ComplaintResponse> getComplaintsByStatus(
            ComplaintStatus status) {

        return complaintRepository
                .findAllByStatus(status)
                .stream()
                .map(complaintMapper::toResponse)
                .toList();
    }

    @Override
    public ComplaintResponse markInProgress(
            Long id,
            ComplaintDecisionRequest request) {

        Complaint complaint =
                complaintRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Complaint not found"));

        if (complaint.getStatus() != ComplaintStatus.OPEN) {
            throw new RuntimeException(
                    "Complaint is not in OPEN state.");
        }

        complaint.setStatus(ComplaintStatus.IN_PROGRESS);
        complaint.setResolvedBy(SecurityUtils.getCurrentUser());
        complaint.setRemarks(request.getRemarks());

        Complaint savedComplaint =
                complaintRepository.save(complaint);

        return complaintMapper.toResponse(savedComplaint);
    }

    @Override
    public ComplaintResponse resolveComplaint(
            Long id,
            ComplaintDecisionRequest request) {

        Complaint complaint =
                complaintRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Complaint not found"));

        if (complaint.getStatus() == ComplaintStatus.RESOLVED ||
                complaint.getStatus() == ComplaintStatus.REJECTED) {

            throw new RuntimeException(
                    "Complaint already closed.");
        }

        complaint.setStatus(ComplaintStatus.RESOLVED);
        complaint.setResolvedBy(SecurityUtils.getCurrentUser());
        complaint.setResolvedAt(LocalDateTime.now());
        complaint.setRemarks(request.getRemarks());

        Complaint savedComplaint =
                complaintRepository.save(complaint);

        return complaintMapper.toResponse(savedComplaint);
    }

    @Override
    public ComplaintResponse rejectComplaint(
            Long id,
            ComplaintDecisionRequest request) {

        Complaint complaint =
                complaintRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Complaint not found"));

        if (complaint.getStatus() == ComplaintStatus.RESOLVED ||
                complaint.getStatus() == ComplaintStatus.REJECTED) {

            throw new RuntimeException(
                    "Complaint already closed.");
        }

        complaint.setStatus(ComplaintStatus.REJECTED);
        complaint.setResolvedBy(SecurityUtils.getCurrentUser());
        complaint.setResolvedAt(LocalDateTime.now());
        complaint.setRemarks(request.getRemarks());

        Complaint savedComplaint =
                complaintRepository.save(complaint);

        return complaintMapper.toResponse(savedComplaint);
    }


}