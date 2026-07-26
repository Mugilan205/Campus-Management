package com.campusmanagement.community.connect.service;

import com.campusmanagement.community.connect.entity.Connection;
import com.campusmanagement.user.entity.User;

import java.util.Optional;

public interface ConnectCommonService {



        User getUser(Long userId);

        Optional<Connection> findConnectionBetween(
                User user1,
                User user2
        );

        Connection getConnectionOrThrow(
                User user1,
                User user2
        );

        void validateSelfConnection(
                User sender,
                User receiver
        );

        Connection getPendingRequestForReceiver(
                Long requestId,
                User receiver
        );

        Connection getPendingRequestForSender(
                Long requestId,
                User sender
        );

        Connection prepareConnectionRequest(
                User sender,
                User receiver
        );

    Connection getAcceptedConnection(
            Long connectionId,
            User currentUser
    );
    }

