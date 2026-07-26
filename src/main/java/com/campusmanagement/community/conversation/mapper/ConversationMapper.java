package com.campusmanagement.community.conversation.mapper;

import com.campusmanagement.community.conversation.dto.ConversationResponse;
import com.campusmanagement.community.conversation.entity.Conversation;
import com.campusmanagement.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ConversationMapper {

    public ConversationResponse toResponse(
            Conversation conversation,
            User currentUser
    ) {

        User otherUser =
                conversation.getUserOne().getId().equals(currentUser.getId())
                        ? conversation.getUserTwo()
                        : conversation.getUserOne();

        return ConversationResponse.builder()
                .id(conversation.getId())
                .otherUserId(otherUser.getId())
                .otherUserName(otherUser.getName())
                .build();
    }
}
