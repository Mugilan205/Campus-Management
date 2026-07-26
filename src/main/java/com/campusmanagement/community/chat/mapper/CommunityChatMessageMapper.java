package com.campusmanagement.community.chat.mapper;

import com.campusmanagement.community.chat.dto.response.CommunityChatMessageResponse;
import com.campusmanagement.community.chat.entity.CommunityChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommunityChatMessageMapper {

    @Mapping(target = "senderId", source = "sender.id")
    @Mapping(target = "senderName", source = "sender.name")
    CommunityChatMessageResponse toResponse(
            CommunityChatMessage message
    );

}
