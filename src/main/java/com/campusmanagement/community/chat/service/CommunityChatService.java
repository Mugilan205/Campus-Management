package com.campusmanagement.community.chat.service;

import com.campusmanagement.community.chat.dto.request.CommunityChatMessageRequest;
import com.campusmanagement.community.chat.dto.response.CommunityChatMessageResponse;

import java.util.List;

public interface CommunityChatService {

    CommunityChatMessageResponse sendMessage(
            Long communityId,
            CommunityChatMessageRequest request);

    List<CommunityChatMessageResponse> getChatHistory(
            Long communityId);

}