package com.campusmanagement.community.connect.service.impl;

import com.campusmanagement.common.exception.BadRequestException;
import com.campusmanagement.common.exception.ForbiddenException;
import com.campusmanagement.common.exception.ResourceNotFoundException;
import com.campusmanagement.community.connect.entity.Connection;
import com.campusmanagement.community.connect.enums.ConnectionStatus;
import com.campusmanagement.community.connect.repository.ConnectionRepository;
import com.campusmanagement.community.connect.service.ConnectCommonService;
import com.campusmanagement.user.entity.User;
import com.campusmanagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ConnectCommonServiceImpl
        implements ConnectCommonService {

    private final UserRepository userRepository;
    private final ConnectionRepository connectionRepository;

    @Override
    public User getUser(Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));
    }

    @Override
    public void validateSelfConnection(
            User sender,
            User receiver
    ) {

        if (sender.getId().equals(receiver.getId())) {

            throw new BadRequestException(
                    "You cannot connect with yourself."
            );

        }
    }

    @Override
    public Optional<Connection> findConnectionBetween(
            User user1,
            User user2
    ) {

        return connectionRepository.findConnectionBetween(
                user1,
                user2
        );

    }

    @Override
    public Connection getPendingRequest(Long requestId) {

        Connection connection =
                connectionRepository.findById(requestId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Connection request not found."
                                ));

        if (connection.getStatus() != ConnectionStatus.PENDING) {

            throw new BadRequestException(
                    "Connection request is no longer pending."
            );

        }

        return connection;

    }

    @Override
    public Connection getConnectionOrThrow(
            User user1,
            User user2
    ) {

        return connectionRepository
                .findConnectionBetween(user1, user2)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Connection not found."
                        ));
    }

    @Override
    public void validateReceiver(
            Connection connection,
            User currentUser
    ) {

        if (!connection.getReceiver()
                .getId()
                .equals(currentUser.getId())) {

            throw new ForbiddenException(
                    "You are not allowed to perform this action."
            );

        }

    }

    @Override
    public void validateAlreadyConnected(
            Connection connection
    ) {

        if (connection.getStatus() == ConnectionStatus.ACCEPTED) {

            throw new BadRequestException(
                    "Users are already connected."
            );

        }

    }



}
