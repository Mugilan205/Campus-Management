package com.campusmanagement.community.connect.mapper;

import com.campusmanagement.community.connect.dto.response.ConnectionResponse;
import com.campusmanagement.community.connect.entity.Connection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConnectMapper {

    @Mapping(target = "senderId", source = "sender.id")
    @Mapping(target = "senderName", source = "sender.name")
    @Mapping(target = "receiverId", source = "receiver.id")
    @Mapping(target = "receiverName", source = "receiver.name")
    ConnectionResponse toResponse(Connection connection);

}
