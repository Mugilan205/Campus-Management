package com.campusmanagement.community.member.mapper;

import com.campusmanagement.community.member.dto.CommunityMemberResponse;
import com.campusmanagement.community.member.entity.CommunityMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommunityMemberMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "joinedAt", source = "joinedAt")
    CommunityMemberResponse toResponse(
            CommunityMember member
    );
}