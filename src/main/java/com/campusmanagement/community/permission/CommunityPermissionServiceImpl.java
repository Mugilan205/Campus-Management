package com.campusmanagement.community.permission;

import com.campusmanagement.community.community.entity.Community;
import com.campusmanagement.community.community.repository.CommunityRepository;
import com.campusmanagement.community.member.entity.CommunityMember;
import com.campusmanagement.community.member.enums.CommunityMemberRole;
import com.campusmanagement.community.member.repository.CommunityMemberRepository;
import com.campusmanagement.security.SecurityUtils;
import com.campusmanagement.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityPermissionServiceImpl implements CommunityPermissionService {

    private final CommunityRepository communityRepository;

    private final CommunityMemberRepository memberRepository;

    private CommunityMember getMembership(Long communityId) {

        Community community =
                communityRepository.findById(communityId)
                        .orElseThrow(() ->
                                new RuntimeException("Community not found"));

        User currentUser =
                SecurityUtils.getCurrentUser();

        return memberRepository
                .findByCommunityAndUser(community, currentUser)
                .orElseThrow(() ->
                        new RuntimeException("You are not a member."));
    }

    @Override
    public boolean isMember(Long communityId) {

        try {
            getMembership(communityId);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean isModerator(Long communityId) {

        CommunityMember member =
                getMembership(communityId);

        return member.getRole() == CommunityMemberRole.MODERATOR
                || member.getRole() == CommunityMemberRole.ADMIN;
    }

    @Override
    public boolean isAdmin(Long communityId) {

        return getMembership(communityId)
                .getRole() == CommunityMemberRole.ADMIN;
    }

    @Override
    public void checkMemberPermission(Long communityId) {

        if (!isMember(communityId)) {
            throw new RuntimeException(
                    "You are not a member of this community.");
        }
    }


    @Override
    public void checkModeratorPermission(Long communityId) {

        if (!isModerator(communityId)) {
            throw new RuntimeException(
                    "Moderator permission required.");
        }
    }

    @Override
    public void checkAdminPermission(Long communityId) {

        if (!isAdmin(communityId)) {
            throw new RuntimeException(
                    "Admin permission required.");
        }
    }





}
