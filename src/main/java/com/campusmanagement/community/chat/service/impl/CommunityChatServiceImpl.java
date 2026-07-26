package com.campusmanagement.community.chat.service.impl;

import com.campusmanagement.community.chat.dto.request.CommunityChatMessageRequest;
import com.campusmanagement.community.chat.dto.response.CommunityChatMessageResponse;
import com.campusmanagement.community.chat.entity.CommunityChatMessage;
import com.campusmanagement.community.chat.mapper.CommunityChatMessageMapper;
import com.campusmanagement.community.chat.repository.CommunityChatMessageRepository;
import com.campusmanagement.community.chat.service.CommunityChatService;
import com.campusmanagement.community.common.CommunityCommonService;
import com.campusmanagement.community.community.entity.Community;
import com.campusmanagement.community.permission.CommunityPermissionService;
import com.campusmanagement.security.SecurityUtils;
import com.campusmanagement.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommunityChatServiceImpl implements CommunityChatService {

    private final CommunityChatMessageRepository repository;

    private final CommunityChatMessageMapper mapper;

    private final CommunityPermissionService permissionService;

    private final CommunityCommonService commonService;

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public CommunityChatMessageResponse sendMessage(
            Long communityId,
            CommunityChatMessageRequest request) {

        permissionService.checkMemberPermission(communityId);

        Community community =
                commonService.getCommunity(communityId);

        User currentUser =
                SecurityUtils.getCurrentUser();

        CommunityChatMessage message =
                CommunityChatMessage.builder()
                        .community(community)
                        .sender(currentUser)
                        .message(request.getMessage())
                        .build();

        CommunityChatMessage saved =
                repository.save(message);

        CommunityChatMessageResponse response =
                mapper.toResponse(saved);

        messagingTemplate.convertAndSend(
                "/topic/community/" + communityId,
                response
        );

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommunityChatMessageResponse> getChatHistory(
            Long communityId) {

        permissionService.checkMemberPermission(communityId);

        Community community =
                commonService.getCommunity(communityId);

        return repository
                .findByCommunityOrderBySentAtAsc(community)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

}