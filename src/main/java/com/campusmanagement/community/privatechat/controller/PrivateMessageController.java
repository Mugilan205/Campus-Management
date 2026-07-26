package com.campusmanagement.community.privatechat.controller;

import com.campusmanagement.community.privatechat.dto.PrivateMessageResponse;
import com.campusmanagement.community.privatechat.service.PrivateMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/private-chat")
@RequiredArgsConstructor
public class PrivateMessageController {

    private final PrivateMessageService privateMessageService;

    @GetMapping("/{conversationId}")
    public ResponseEntity<List<PrivateMessageResponse>>
    getHistory(
            @PathVariable Long conversationId
    ) {

        return ResponseEntity.ok(
                privateMessageService.getHistory(
                        conversationId
                )
        );
    }

    @PatchMapping("/{conversationId}/read")
    public ResponseEntity<Void> markAsRead(
            @PathVariable Long conversationId
    ) {

        privateMessageService.markAsRead(
                conversationId
        );

        return ResponseEntity.noContent().build();
    }
}
