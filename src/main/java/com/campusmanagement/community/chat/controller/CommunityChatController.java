package com.campusmanagement.community.chat.controller;

import com.campusmanagement.community.chat.dto.response.CommunityChatMessageResponse;
import com.campusmanagement.community.chat.service.CommunityChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/communities")
public class CommunityChatController {

    private final CommunityChatService chatService;

    @GetMapping("/{communityId}/chat")
    public ResponseEntity<List<CommunityChatMessageResponse>>
    getChatHistory(
            @PathVariable Long communityId) {

        return ResponseEntity.ok(
                chatService.getChatHistory(communityId));
    }

}