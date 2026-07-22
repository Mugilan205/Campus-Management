package com.campusmanagement.complaint.mapper;

import com.campusmanagement.complaint.dto.ComplaintResponse;
import com.campusmanagement.complaint.entity.Complaint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComplaintMapper {

    @Mapping(target = "createdBy", source = "createdBy.name")
    @Mapping(target = "resolvedBy", source = "resolvedBy.name")
    ComplaintResponse toResponse(Complaint complaint);

}