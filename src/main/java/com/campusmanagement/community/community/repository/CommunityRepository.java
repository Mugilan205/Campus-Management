package com.campusmanagement.community.community.repository;

import com.campusmanagement.community.community.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository
        extends JpaRepository<Community, Long> {

    boolean existsByName(String name);

}