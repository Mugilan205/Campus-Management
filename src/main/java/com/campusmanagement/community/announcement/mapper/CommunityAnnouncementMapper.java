package com.campusmanagement.community.announcement.mapper;

import com.campusmanagement.community.announcement.dto.CommunityAnnouncementResponse;
import com.campusmanagement.community.announcement.entity.CommunityAnnouncement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommunityAnnouncementMapper {

    @Mapping(target = "communityId", source = "community.id")
    @Mapping(target = "communityName", source = "community.name")
    @Mapping(target = "createdBy", source = "createdBy.name")
    CommunityAnnouncementResponse toResponse(
            CommunityAnnouncement announcement
    );

}