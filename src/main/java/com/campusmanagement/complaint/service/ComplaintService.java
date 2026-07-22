package com.campusmanagement.complaint.service;


import com.campusmanagement.complaint.dto.*;
import com.campusmanagement.complaint.enums.ComplaintStatus;

import java.util.List;

public interface ComplaintService {

    ComplaintResponse createComplaint(ComplaintRequest request);

    List<ComplaintResponse> getMyComplaints();

    List<ComplaintResponse> getAllComplaints();

    ComplaintResponse markInProgress(
            Long id,
            ComplaintDecisionRequest request
    );

    ComplaintResponse resolveComplaint(
            Long id,
            ComplaintDecisionRequest request
    );

    ComplaintResponse rejectComplaint(
            Long id,
            ComplaintDecisionRequest request
    );

    List<ComplaintResponse> getComplaintsByStatus(
            ComplaintStatus status);
}