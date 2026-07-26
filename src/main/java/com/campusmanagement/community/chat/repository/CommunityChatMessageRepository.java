package com.campusmanagement.community.chat.repository;

import com.campusmanagement.community.chat.entity.CommunityChatMessage;
import com.campusmanagement.community.community.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityChatMessageRepository
        extends JpaRepository<CommunityChatMessage, Long> {

    List<CommunityChatMessage>
    findByCommunityOrderBySentAtAsc(
            Community community
    );

}
