package com.campusmanagement.community.announcement.repository;

import com.campusmanagement.community.announcement.entity.CommunityAnnouncement;
import com.campusmanagement.community.community.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityAnnouncementRepository
        extends JpaRepository<CommunityAnnouncement, Long> {

    List<CommunityAnnouncement> findByCommunityOrderByCreatedAtDesc(
            Community community
    );

}