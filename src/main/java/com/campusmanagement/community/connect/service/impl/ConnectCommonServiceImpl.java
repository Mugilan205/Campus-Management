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
    public Connection prepareConnectionRequest(
            User sender,
            User receiver
    ) {

        validateSelfConnection(sender, receiver);

        Optional<Connection> existing =
                connectionRepository.findConnectionBetween(
                        sender,
                        receiver
                );

        if (existing.isEmpty()) {

            return Connection.builder()
                    .sender(sender)
                    .receiver(receiver)
                    .status(ConnectionStatus.PENDING)
                    .build();
        }

        Connection connection = existing.get();

        switch (connection.getStatus()) {

            case PENDING ->
                    throw new BadRequestException(
                            "Connection request already exists."
                    );

            case ACCEPTED ->
                    throw new BadRequestException(
                            "Users are already connected."
                    );

            case REJECTED -> {

                connection.setSender(sender);
                connection.setReceiver(receiver);
                connection.setStatus(ConnectionStatus.PENDING);

                return connection;
            }
        }

        throw new IllegalStateException("Unknown connection state.");
    }

    @Override
    public Connection getPendingRequestForReceiver(
            Long requestId,
            User receiver
    ) {

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

        if (!connection.getReceiver()
                .getId()
                .equals(receiver.getId())) {

            throw new ForbiddenException(
                    "Only the receiver can perform this action."
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
    public Connection getPendingRequestForSender(
            Long requestId,
            User sender
    ) {

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

        if (!connection.getSender()
                .getId()
                .equals(sender.getId())) {

            throw new ForbiddenException(
                    "Only the sender can perform this action."
            );
        }

        return connection;
    }

    @Override
    public Connection getAcceptedConnection(
            Long connectionId,
            User currentUser
    ) {

        Connection connection =
                connectionRepository.findById(connectionId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Connection not found."
                                ));

        if (connection.getStatus() != ConnectionStatus.ACCEPTED) {

            throw new BadRequestException(
                    "Users are not connected."
            );

        }

        boolean member =
                connection.getSender().getId().equals(currentUser.getId())
                        ||
                        connection.getReceiver().getId().equals(currentUser.getId());

        if (!member) {

            throw new ForbiddenException(
                    "Access denied."
            );

        }

        return connection;
    }


}
