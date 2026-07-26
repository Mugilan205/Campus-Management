package com.campusmanagement.community.connect.controller;

import com.campusmanagement.community.connect.dto.response.ConnectionResponse;
import com.campusmanagement.community.connect.service.ConnectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/request/{requestId}/accept")
    public ResponseEntity<ConnectionResponse> acceptConnectionRequest(
            @PathVariable Long requestId
    ) {

        return ResponseEntity.ok(
                connectService.acceptConnectionRequest(requestId)
        );

    }

    @PatchMapping("/request/{requestId}/reject")
    public ResponseEntity<ConnectionResponse> rejectConnectionRequest(
            @PathVariable Long requestId
    ) {
        return ResponseEntity.ok(
                connectService.rejectConnectionRequest(requestId)
        );
    }

    @DeleteMapping("/request/{requestId}")
    public ResponseEntity<Void> cancelConnectionRequest(
            @PathVariable Long requestId
    ) {

        connectService.cancelConnectionRequest(requestId);

        return ResponseEntity.noContent().build();
    }



}
