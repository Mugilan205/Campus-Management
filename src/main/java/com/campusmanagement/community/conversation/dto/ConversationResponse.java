package com.campusmanagement.community.conversation.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConversationResponse {

    private Long id;

    private Long otherUserId;

    private String otherUserName;
}
