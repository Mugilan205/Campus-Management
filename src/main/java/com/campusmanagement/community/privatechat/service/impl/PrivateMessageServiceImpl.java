package com.campusmanagement.community.privatechat.service.impl;

import com.campusmanagement.community.conversation.common.ConversationCommonService;
import com.campusmanagement.community.conversation.entity.Conversation;
import com.campusmanagement.community.privatechat.dto.PrivateMessageRequest;
import com.campusmanagement.community.privatechat.dto.PrivateMessageResponse;
import com.campusmanagement.community.privatechat.entity.PrivateMessage;
import com.campusmanagement.community.privatechat.mapper.PrivateMessageMapper;
import com.campusmanagement.community.privatechat.repository.PrivateMessageRepository;
import com.campusmanagement.community.privatechat.service.PrivateMessageService;
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
public class PrivateMessageServiceImpl
        implements PrivateMessageService {

    private final PrivateMessageRepository privateMessageRepository;

    private final ConversationCommonService conversationCommonService;

    private final PrivateMessageMapper privateMessageMapper;

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public PrivateMessageResponse sendMessage(
            Long conversationId,
            PrivateMessageRequest request
    ) {

        User sender =
                SecurityUtils.getCurrentUser();

        Conversation conversation =
                conversationCommonService
                        .getConversationForUser(
                                conversationId,
                                sender
                        );

        PrivateMessage message =
                PrivateMessage.builder()
                        .conversation(conversation)
                        .sender(sender)
                        .message(request.getMessage())
                        .build();

        privateMessageRepository.save(message);

        PrivateMessageResponse response =
                privateMessageMapper.toResponse(message);

        messagingTemplate.convertAndSend(
                "/topic/private/" + conversationId,
                response
        );

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrivateMessageResponse> getHistory(
            Long conversationId
    ) {

        User currentUser =
                SecurityUtils.getCurrentUser();

        Conversation conversation =
                conversationCommonService
                        .getConversationForUser(
                                conversationId,
                                currentUser
                        );

        return privateMessageRepository
                .findByConversationOrderByCreatedAtAsc(
                        conversation
                )
                .stream()
                .map(privateMessageMapper::toResponse)
                .toList();
    }

    @Override
    public void markAsRead(
            Long conversationId
    ) {

        User currentUser =
                SecurityUtils.getCurrentUser();

        Conversation conversation =
                conversationCommonService
                        .getConversationForUser(
                                conversationId,
                                currentUser
                        );

        List<PrivateMessage> unread =
                privateMessageRepository
                        .findByConversationAndIsReadFalse(
                                conversation
                        );

        unread.forEach(message -> {

            if (!message.getSender()
                    .getId()
                    .equals(currentUser.getId())) {

                message.setIsRead(true);
            }
        });

        privateMessageRepository.saveAll(unread);
    }
}
