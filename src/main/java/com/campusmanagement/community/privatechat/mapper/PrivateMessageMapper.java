package com.campusmanagement.community.privatechat.mapper;

import com.campusmanagement.community.privatechat.dto.PrivateMessageResponse;
import com.campusmanagement.community.privatechat.entity.PrivateMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrivateMessageMapper {

    @Mapping(target = "conversationId", source = "conversation.id")
    @Mapping(target = "senderId", source = "sender.id")
    @Mapping(target = "senderName", source = "sender.name")
    PrivateMessageResponse toResponse(PrivateMessage message);
}
