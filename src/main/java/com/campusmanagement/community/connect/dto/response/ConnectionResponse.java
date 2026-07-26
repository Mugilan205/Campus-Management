package com.campusmanagement.community.connect.dto.response;

import com.campusmanagement.community.connect.enums.ConnectionStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConnectionResponse {

    private Long id;

    private Long senderId;
    private String senderName;

    private Long receiverId;
    private String receiverName;

    private ConnectionStatus status;

    private LocalDateTime createdAt;
}