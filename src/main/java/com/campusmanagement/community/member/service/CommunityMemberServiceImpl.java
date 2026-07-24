package com.campusmanagement.community.member.service;

import com.campusmanagement.community.common.service.CommunityCommonService;
import com.campusmanagement.community.community.entity.Community;
import com.campusmanagement.community.member.dto.CommunityMemberResponse;
import com.campusmanagement.community.member.dto.UpdateCommunityMemberRoleRequest;
import com.campusmanagement.community.member.entity.CommunityMember;
import com.campusmanagement.community.member.mapper.CommunityMemberMapper;
import com.campusmanagement.community.member.repository.CommunityMemberRepository;
import com.campusmanagement.community.permission.CommunityPermissionService;
import com.campusmanagement.security.SecurityUtils;
import com.campusmanagement.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommunityMemberServiceImpl
        implements CommunityMemberService {

    private final CommunityMemberRepository memberRepository;

    private final CommunityMemberMapper mapper;

    private final CommunityPermissionService permissionService;

    private final CommunityCommonService commonService;

    @Override
    @Transactional(readOnly = true)
    public List<CommunityMemberResponse> getMembers(
            Long communityId) {

        permissionService.checkMemberPermission(communityId);

        Community community =
                commonService.getCommunity(communityId);

        return memberRepository
                .findByCommunityOrderByJoinedAtAsc(community)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public CommunityMemberResponse updateMemberRole(
            Long communityId,
            Long userId,
            UpdateCommunityMemberRoleRequest request) {

        permissionService.checkAdminPermission(communityId);

        CommunityMember member =
                commonService.getMembership(
                        communityId,
                        userId);

        member.setRole(request.getRole());

        return mapper.toResponse(
                memberRepository.save(member));
    }

    @Override
    public void removeMember(
            Long communityId,
            Long userId) {

        permissionService.checkAdminPermission(communityId);

        CommunityMember member =
                commonService.getMembership(
                        communityId,
                        userId);

        memberRepository.delete(member);
    }

    @Override
    public void leaveCommunity(
            Long communityId) {

        User currentUser =
                SecurityUtils.getCurrentUser();

        CommunityMember member =
                commonService.getMembership(
                        communityId,
                        currentUser.getId());

        memberRepository.delete(member);
    }




}
