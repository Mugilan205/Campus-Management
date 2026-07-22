package com.campusmanagement.announcement.repository;

import com.campusmanagement.announcement.entity.Announcement;
import com.campusmanagement.announcement.enums.AnnouncementStatus;
import com.campusmanagement.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository
        extends JpaRepository<Announcement, Long> {

    List<Announcement> findByCreatedBy(User user);

    List<Announcement> findAllByStatus(
            AnnouncementStatus status
    );
}