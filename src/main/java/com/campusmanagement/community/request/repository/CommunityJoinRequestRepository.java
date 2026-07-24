package com.campusmanagement.community.request.repository;

import com.campusmanagement.community.community.entity.Community;
import com.campusmanagement.community.member.entity.CommunityMember;
import com.campusmanagement.community.request.entity.CommunityJoinRequest;
import com.campusmanagement.community.request.enums.JoinRequestStatus;
import com.campusmanagement.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommunityJoinRequestRepository
        extends JpaRepository<CommunityJoinRequest, Long> {

    List<CommunityJoinRequest> findByCommunityAndStatus(
            Community community,
            JoinRequestStatus status
    );

    List<CommunityJoinRequest> findByRequestedBy(
            User user
    );

    boolean existsByCommunityAndRequestedByAndStatus(
            Community community,
            User user,
            JoinRequestStatus status
    );


}