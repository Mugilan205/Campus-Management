package com.campusmanagement.community.chat.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityChatMessageResponse {

    private Long id;

    private Long senderId;

    private String senderName;

    private String message;

    private LocalDateTime sentAt;

}
