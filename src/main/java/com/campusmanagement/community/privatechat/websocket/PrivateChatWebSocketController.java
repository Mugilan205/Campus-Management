package com.campusmanagement.community.privatechat.websocket;

import com.campusmanagement.community.privatechat.dto.PrivateMessageRequest;
import com.campusmanagement.community.privatechat.service.PrivateMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PrivateChatWebSocketController {

    private final PrivateMessageService privateMessageService;

    @MessageMapping("/private/{conversationId}")
    public void sendMessage(
            @DestinationVariable Long conversationId,
            @Payload PrivateMessageRequest request
    ) {

        privateMessageService.sendMessage(
                conversationId,
                request
        );
    }
}
