package com.campusmanagement.community.permission;

public interface CommunityPermissionService {

    void checkMemberPermission(Long communityId);

    void checkModeratorPermission(Long communityId);

    void checkAdminPermission(Long communityId);

    boolean isMember(Long communityId);

    boolean isModerator(Long communityId);

    boolean isAdmin(Long communityId);

}
