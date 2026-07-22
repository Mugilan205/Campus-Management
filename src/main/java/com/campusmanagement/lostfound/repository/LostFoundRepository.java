package com.campusmanagement.lostfound.repository;

import com.campusmanagement.lostfound.enitity.LostFound;
import com.campusmanagement.lostfound.enums.LostFoundStatus;
import com.campusmanagement.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LostFoundRepository
        extends JpaRepository<LostFound, Long> {

    List<LostFound> findByCreatedBy(User user);

    List<LostFound> findAllByStatus(
            LostFoundStatus status);

}