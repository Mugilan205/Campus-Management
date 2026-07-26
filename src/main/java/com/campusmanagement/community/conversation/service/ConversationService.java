package com.campusmanagement.community.conversation.service;

import com.campusmanagement.community.conversation.dto.ConversationResponse;
import com.campusmanagement.community.conversation.entity.Conversation;
import com.campusmanagement.user.entity.User;

import java.util.List;

public interface ConversationService {

    Conversation createConversation(
            User userOne,
            User userTwo
    );

    List<ConversationResponse> getMyConversations();
}
