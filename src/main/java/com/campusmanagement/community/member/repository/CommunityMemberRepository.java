package com.campusmanagement.community.member.repository;

import com.campusmanagement.community.community.entity.Community;
import com.campusmanagement.community.member.entity.CommunityMember;
import com.campusmanagement.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommunityMemberRepository
        extends JpaRepository<CommunityMember, Long> {

    List<CommunityMember> findByCommunity(
            Community community);

    List<CommunityMember> findByUser(
            User user);

    Optional<CommunityMember> findByCommunityAndUser(
            Community community,
            User user);

    boolean existsByCommunityAndUser(
            Community community,
            User user);

    Optional<CommunityMember> findByCommunityIdAndUserId(
            Long communityId,
            Long userId
    );

    long countByCommunity(
            Community community);

    List<CommunityMember> findByCommunityOrderByJoinedAtAsc(Community community);
}