package com.campusmanagement.community.conversation.service.impl;

import com.campusmanagement.community.conversation.dto.ConversationResponse;
import com.campusmanagement.community.conversation.entity.Conversation;
import com.campusmanagement.community.conversation.mapper.ConversationMapper;
import com.campusmanagement.community.conversation.repository.ConversationRepository;
import com.campusmanagement.community.conversation.service.ConversationService;
import com.campusmanagement.security.SecurityUtils;
import com.campusmanagement.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ConversationServiceImpl
        implements ConversationService {

    private final ConversationRepository conversationRepository;

    private final ConversationMapper conversationMapper;

    @Override
    public Conversation createConversation(
            User userOne,
            User userTwo
    ) {

        final User orderedUserOne =
                userOne.getId() > userTwo.getId() ? userTwo : userOne;
        final User orderedUserTwo =
                userOne.getId() > userTwo.getId() ? userOne : userTwo;

        return conversationRepository
                .findByUserOneAndUserTwo(orderedUserOne, orderedUserTwo)
                .orElseGet(() ->
                        conversationRepository.save(
                                Conversation.builder()
                                        .userOne(orderedUserOne)
                                        .userTwo(orderedUserTwo)
                                        .build()
                        )
                );
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConversationResponse> getMyConversations() {

        User currentUser =
                SecurityUtils.getCurrentUser();

        return conversationRepository
                .findByParticipant(currentUser)
                .stream()
                .map(conversation ->
                        conversationMapper.toResponse(
                                conversation,
                                currentUser
                        ))
                .toList();
    }
}
