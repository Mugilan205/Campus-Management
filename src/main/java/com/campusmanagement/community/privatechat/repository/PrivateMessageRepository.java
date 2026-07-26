package com.campusmanagement.community.privatechat.repository;

import com.campusmanagement.community.conversation.entity.Conversation;
import com.campusmanagement.community.privatechat.entity.PrivateMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrivateMessageRepository
        extends JpaRepository<PrivateMessage, Long> {

    List<PrivateMessage>
    findByConversationOrderByCreatedAtAsc(
            Conversation conversation
    );

    Optional<PrivateMessage>
    findFirstByConversationOrderByCreatedAtDesc(
            Conversation conversation
    );

    List<PrivateMessage>
    findByConversationAndIsReadFalse(
            Conversation conversation
    );

    long countByConversationAndIsReadFalse(
            Conversation conversation
    );
}
