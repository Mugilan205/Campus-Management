package com.campusmanagement.lostfound.mapper;

import com.campusmanagement.lostfound.dto.LostFoundResponse;
import com.campusmanagement.lostfound.enitity.LostFound;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LostFoundMapper {

    @Mapping(target = "createdBy", source = "createdBy.name")
    @Mapping(target = "approvedBy", source = "approvedBy.name")
    @Mapping(target = "claimedBy", source = "claimedBy.name")
    LostFoundResponse toResponse(LostFound lostFound);

}