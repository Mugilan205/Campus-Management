package com.campusmanagement.complaint.repository;

import com.campusmanagement.complaint.entity.Complaint;
import com.campusmanagement.complaint.enums.ComplaintStatus;
import com.campusmanagement.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository
        extends JpaRepository<Complaint, Long> {

    List<Complaint> findByCreatedBy(User user);

    List<Complaint> findAllByStatus(ComplaintStatus status);


}