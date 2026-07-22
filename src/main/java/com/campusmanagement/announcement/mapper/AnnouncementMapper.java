package com.campusmanagement.announcement.mapper;

import com.campusmanagement.announcement.dto.AnnouncementResponse;
import com.campusmanagement.announcement.entity.Announcement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {

    @Mapping(target = "createdBy", source = "createdBy.name")
    @Mapping(target = "approvedBy", source = "approvedBy.name")
    AnnouncementResponse toResponse(Announcement announcement);
}