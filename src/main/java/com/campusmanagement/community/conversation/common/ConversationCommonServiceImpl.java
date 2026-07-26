package com.campusmanagement.community.conversation.common;

import com.campusmanagement.common.exception.ForbiddenException;
import com.campusmanagement.common.exception.ResourceNotFoundException;
import com.campusmanagement.community.conversation.entity.Conversation;
import com.campusmanagement.community.conversation.repository.ConversationRepository;
import com.campusmanagement.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversationCommonServiceImpl
        implements ConversationCommonService {

    private final ConversationRepository conversationRepository;

    @Override
    public Conversation getConversation(Long conversationId) {

        return conversationRepository.findById(conversationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Conversation not found."
                        ));
    }

    @Override
    public Conversation getConversationForUser(
            Long conversationId,
            User user
    ) {

        Conversation conversation =
                getConversation(conversationId);

        boolean participant =
                conversation.getUserOne().getId().equals(user.getId())
                        ||
                        conversation.getUserTwo().getId().equals(user.getId());

        if (!participant) {

            throw new ForbiddenException(
                    "You are not a participant of this conversation."
            );
        }

        return conversation;
    }
}
