package com.campusmanagement.community.connect.service.impl;

import com.campusmanagement.common.exception.BadRequestException;
import com.campusmanagement.community.connect.dto.response.ConnectionResponse;
import com.campusmanagement.community.connect.entity.Connection;
import com.campusmanagement.community.connect.enums.ConnectionStatus;
import com.campusmanagement.community.connect.mapper.ConnectMapper;
import com.campusmanagement.community.connect.repository.ConnectionRepository;
import com.campusmanagement.community.connect.service.ConnectCommonService;
import com.campusmanagement.community.connect.service.ConnectService;
import com.campusmanagement.security.SecurityUtils;
import com.campusmanagement.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ConnectServiceImpl implements ConnectService {

    private final ConnectionRepository connectionRepository;
    private final ConnectCommonService connectCommonService;
    private final ConnectMapper connectMapper;


    @Override
    public ConnectionResponse sendConnectionRequest(Long receiverId) {

        User sender =
                SecurityUtils.getCurrentUser();

        User receiver =
                connectCommonService.getUser(receiverId);

        connectCommonService.validateSelfConnection(
                sender,
                receiver
        );

        Optional<Connection> existingConnection =
                connectCommonService.findConnectionBetween(
                        sender,
                        receiver
                );

        if (existingConnection.isPresent()) {

            Connection connection = existingConnection.get();

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

                    connectionRepository.save(connection);

                    return connectMapper.toResponse(connection);
                }

            }

        }

        Connection connection =
                Connection.builder()
                        .sender(sender)
                        .receiver(receiver)
                        .status(ConnectionStatus.PENDING)
                        .build();

        connectionRepository.save(connection);

        return connectMapper.toResponse(connection);
    }
}