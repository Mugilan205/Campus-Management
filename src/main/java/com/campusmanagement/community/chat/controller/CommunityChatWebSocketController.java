package com.campusmanagement.community.chat.controller;

import com.campusmanagement.community.chat.dto.request.CommunityChatMessageRequest;
import com.campusmanagement.community.chat.service.CommunityChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CommunityChatWebSocketController {

    private final CommunityChatService chatService;

    @MessageMapping("/chat/{communityId}")
    public void sendMessage(
            @DestinationVariable Long communityId,
            CommunityChatMessageRequest request) {

        chatService.sendMessage(
                communityId,
                request);
    }
}
