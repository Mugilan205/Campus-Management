package com.campusmanagement.community.community.service;

import com.campusmanagement.community.community.dto.CommunityRequest;
import com.campusmanagement.community.community.dto.CommunityResponse;
import com.campusmanagement.community.community.dto.CommunityUpdateRequest;
import com.campusmanagement.community.community.entity.Community;
import com.campusmanagement.community.community.mapper.CommunityMapper;
import com.campusmanagement.community.community.repository.CommunityRepository;
import com.campusmanagement.community.member.entity.CommunityMember;
import com.campusmanagement.community.member.enums.CommunityMemberRole;
import com.campusmanagement.community.member.repository.CommunityMemberRepository;
import com.campusmanagement.security.SecurityUtils;
import com.campusmanagement.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;

    private final CommunityMemberRepository communityMemberRepository;

    private final CommunityMapper communityMapper;

    @Override
    public CommunityResponse createCommunity(
            CommunityRequest request) {

        if (communityRepository.existsByName(request.getName())) {
            throw new RuntimeException("Community already exists.");
        }

        User currentUser = SecurityUtils.getCurrentUser();

        Community community = Community.builder()
                .name(request.getName())
                .description(request.getDescription())
                .iconUrl(request.getIconUrl())
                .bannerUrl(request.getBannerUrl())
                .visibility(request.getVisibility())
                .createdBy(currentUser)
                .build();

        Community savedCommunity =
                communityRepository.save(community);

        CommunityMember admin =
                CommunityMember.builder()
                        .community(savedCommunity)
                        .user(currentUser)
                        .role(CommunityMemberRole.ADMIN)
                        .build();

        communityMemberRepository.save(admin);

        CommunityResponse response =
                communityMapper.toResponse(savedCommunity);

        response.setMemberCount(1L);

        return response;
    }

    @Override
    public List<CommunityResponse> getAllCommunities() {

        return communityRepository.findAll()
                .stream()
                .map(community -> {

                    CommunityResponse response =
                            communityMapper.toResponse(community);

                    response.setMemberCount(
                            communityMemberRepository
                                    .countByCommunity(community));

                    return response;
                })
                .toList();
    }

    @Override
    public CommunityResponse updateCommunity(
            Long id,
            CommunityUpdateRequest request) {

        Community community =
                communityRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Community not found."));

        if (request.getDescription() != null)
            community.setDescription(request.getDescription());

        if (request.getIconUrl() != null)
            community.setIconUrl(request.getIconUrl());

        if (request.getBannerUrl() != null)
            community.setBannerUrl(request.getBannerUrl());

        if (request.getVisibility() != null)
            community.setVisibility(request.getVisibility());

        Community saved =
                communityRepository.save(community);

        CommunityResponse response =
                communityMapper.toResponse(saved);

        response.setMemberCount(
                communityMemberRepository
                        .countByCommunity(saved));

        return response;
    }

    @Override
    public CommunityResponse getCommunity(Long id) {

        Community community =
                communityRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Community not found."));

        CommunityResponse response =
                communityMapper.toResponse(community);

        response.setMemberCount(
                communityMemberRepository
                        .countByCommunity(community));

        return response;
    }

    @Override
    public void deleteCommunity(Long id) {

        Community community =
                communityRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Community not found."));

        communityRepository.delete(community);
    }


}
