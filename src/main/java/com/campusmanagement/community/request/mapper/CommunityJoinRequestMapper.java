package com.campusmanagement.community.request.mapper;

import com.campusmanagement.community.request.dto.CommunityJoinRequestResponse;
import com.campusmanagement.community.request.entity.CommunityJoinRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommunityJoinRequestMapper {

    @Mapping(target = "communityId",
            source = "community.id")
    @Mapping(target = "communityName",
            source = "community.name")
    @Mapping(target = "requestedById",
            source = "requestedBy.id")
    @Mapping(target = "requestedByName",
            source = "requestedBy.name")
    @Mapping(target = "reviewedBy",
            source = "reviewedBy.name")
    CommunityJoinRequestResponse toResponse(
            CommunityJoinRequest request);

}