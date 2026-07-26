package com.campusmanagement.community.privatechat.service;

import com.campusmanagement.community.privatechat.dto.PrivateMessageRequest;
import com.campusmanagement.community.privatechat.dto.PrivateMessageResponse;

import java.util.List;

public interface PrivateMessageService {

    PrivateMessageResponse sendMessage(
            Long conversationId,
            PrivateMessageRequest request
    );

    List<PrivateMessageResponse> getHistory(
            Long conversationId
    );

    void markAsRead(
            Long conversationId
    );
}
