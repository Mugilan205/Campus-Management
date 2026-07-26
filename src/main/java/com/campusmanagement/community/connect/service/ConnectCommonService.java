package com.campusmanagement.community.connect.service;

import com.campusmanagement.community.connect.entity.Connection;
import com.campusmanagement.user.entity.User;

import java.util.Optional;

public interface ConnectCommonService {

    User getUser(Long userId);

    void validateSelfConnection(User sender, User receiver);

    Optional<Connection> findConnectionBetween(
            User user1,
            User user2
    );

    Connection getPendingRequest(Long requestId);

    Connection getConnectionOrThrow(
            User user1,
            User user2
    );

    void validateReceiver(
            Connection connection,
            User currentUser
    );

    void validateAlreadyConnected(Connection connection);

}