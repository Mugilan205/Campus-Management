package com.campusmanagement.community.conversation.common;

import com.campusmanagement.community.conversation.entity.Conversation;
import com.campusmanagement.user.entity.User;

public interface ConversationCommonService {

    Conversation getConversation(Long conversationId);

    Conversation getConversationForUser(
            Long conversationId,
            User user
    );
}
