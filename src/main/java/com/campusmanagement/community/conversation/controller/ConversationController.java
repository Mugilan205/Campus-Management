package com.campusmanagement.community.conversation.controller;

import com.campusmanagement.community.conversation.dto.ConversationResponse;
import com.campusmanagement.community.conversation.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/conversations")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @GetMapping
    public ResponseEntity<List<ConversationResponse>>
    getMyConversations() {

        return ResponseEntity.ok(
                conversationService.getMyConversations()
        );
    }
}
