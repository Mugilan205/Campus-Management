package com.campusmanagement.community.conversation.repository;

import com.campusmanagement.community.conversation.entity.Conversation;
import com.campusmanagement.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepository
        extends JpaRepository<Conversation, Long> {

    Optional<Conversation> findByUserOneAndUserTwo(
            User userOne,
            User userTwo
    );

    @Query("""
            SELECT c
            FROM Conversation c
            WHERE
            c.userOne = :user
            OR
            c.userTwo = :user
            """)
    List<Conversation> findByParticipant(User user);
}
