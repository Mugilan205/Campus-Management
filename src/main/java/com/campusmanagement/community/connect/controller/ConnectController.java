package com.campusmanagement.community.connect.controller;

import com.campusmanagement.community.connect.service.ConnectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/connect")
@RequiredArgsConstructor
public class ConnectController {

    private final ConnectService connectService;

    @PostMapping("/request/{receiverId}")
    public ResponseEntity<ConnectionResponse> sendRequest(
            @PathVariable Long receiverId
    ) {

        return ResponseEntity.ok(
                connectService.sendConnectionRequest(receiverId)
        );

    }

}
