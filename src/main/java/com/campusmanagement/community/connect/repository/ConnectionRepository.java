package com.campusmanagement.community.connect.repository;

import com.campusmanagement.community.connect.entity.Connection;
import com.campusmanagement.community.connect.enums.ConnectionStatus;
import com.campusmanagement.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConnectionRepository
        extends JpaRepository<Connection, Long> {

    @Query("""
        SELECT c
        FROM Connection c
        WHERE
            (c.sender = :user1 AND c.receiver = :user2)
            OR
            (c.sender = :user2 AND c.receiver = :user1)
    """)
    Optional<Connection> findConnectionBetween(
            User user1,
            User user2
    );

    List<Connection> findByReceiverAndStatus(
            User receiver,
            ConnectionStatus status
    );

    List<Connection> findBySenderAndStatus(
            User sender,
            ConnectionStatus status
    );

    @Query("""
        SELECT c
        FROM Connection c
        WHERE
        (
            c.sender = :user
            OR
            c.receiver = :user
        )
        AND c.status = :status
    """)
    List<Connection> findConnectionsByStatus(
            User user,
            ConnectionStatus status
    );
}
