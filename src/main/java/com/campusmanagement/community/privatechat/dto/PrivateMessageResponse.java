package com.campusmanagement.community.privatechat.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrivateMessageResponse {

    private Long id;

    private Long conversationId;

    private Long senderId;

    private String senderName;

    private String message;

    private Boolean isRead;

    private LocalDateTime createdAt;
}
