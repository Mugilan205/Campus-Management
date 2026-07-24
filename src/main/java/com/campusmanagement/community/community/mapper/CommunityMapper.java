package com.campusmanagement.community.community.mapper;

import com.campusmanagement.community.community.dto.CommunityResponse;
import com.campusmanagement.community.community.entity.Community;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommunityMapper {

    @Mapping(target = "createdBy", source = "createdBy.name")
    @Mapping(target = "memberCount", ignore = true)
    CommunityResponse toResponse(Community community);

}
