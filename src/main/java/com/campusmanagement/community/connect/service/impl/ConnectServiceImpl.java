package com.campusmanagement.community.connect.service.impl;

import com.campusmanagement.community.connect.dto.response.ConnectionResponse;
import com.campusmanagement.community.connect.entity.Connection;
import com.campusmanagement.community.connect.enums.ConnectionStatus;
import com.campusmanagement.community.connect.mapper.ConnectMapper;
import com.campusmanagement.community.connect.repository.ConnectionRepository;
import com.campusmanagement.community.connect.service.ConnectCommonService;
import com.campusmanagement.community.connect.service.ConnectService;
import com.campusmanagement.community.conversation.service.ConversationService;
import com.campusmanagement.security.SecurityUtils;
import com.campusmanagement.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ConnectServiceImpl implements ConnectService {

    private final ConnectionRepository connectionRepository;
    private final ConnectCommonService connectCommonService;
    private final ConnectMapper connectMapper;
    private final ConversationService conversationService;


    @Override
    @Transactional
    public ConnectionResponse sendConnectionRequest(
            Long receiverId
    ) {

        User sender = SecurityUtils.getCurrentUser();

        User receiver =
                connectCommonService.getUser(receiverId);

        Connection connection =
                connectCommonService.prepareConnectionRequest(
                        sender,
                        receiver
                );

        connectionRepository.save(connection);

        return connectMapper.toResponse(connection);
    }


    @Override
    @Transactional
    public ConnectionResponse acceptConnectionRequest(
            Long requestId
    ) {

        User receiver =
                SecurityUtils.getCurrentUser();

        Connection connection =
                connectCommonService
                        .getPendingRequestForReceiver(
                                requestId,
                                receiver
                        );

        connection.setStatus(ConnectionStatus.ACCEPTED);

        connectionRepository.save(connection);

        conversationService.createConversation(
                connection.getSender(),
                connection.getReceiver()
        );

        return connectMapper.toResponse(connection);
    }

    @Override
    @Transactional
    public ConnectionResponse rejectConnectionRequest(Long requestId) {

        User receiver =
                SecurityUtils.getCurrentUser();

        Connection connection =
                connectCommonService.getPendingRequestForReceiver(
                        requestId,
                        receiver
                );

        connection.setStatus(ConnectionStatus.REJECTED);

        connectionRepository.save(connection);

        return connectMapper.toResponse(connection);
    }

    @Override
    @Transactional
    public void cancelConnectionRequest(Long requestId) {

        User sender =
                SecurityUtils.getCurrentUser();


        Connection connection =
                connectCommonService.getPendingRequestForSender(
                        requestId,
                        sender
                );

        connectionRepository.delete(connection);
    }

    @Override
    @Transactional
    public void removeConnection(Long connectionId) {

        User currentUser =
                SecurityUtils.getCurrentUser();

        Connection connection =
                connectCommonService.getAcceptedConnection(
                        connectionId,
                        currentUser
                );

        connectionRepository.delete(connection);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConnectionResponse> getIncomingRequests() {

        User receiver =
                SecurityUtils.getCurrentUser();
        return connectionRepository
                .findByReceiverAndStatus(
                        receiver,
                        ConnectionStatus.PENDING
                )
                .stream()
                .map(connectMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ConnectionResponse> getOutgoingRequests() {

        User sender =
                SecurityUtils.getCurrentUser();
        return connectionRepository
                .findBySenderAndStatus(
                        sender,
                        ConnectionStatus.PENDING
                )
                .stream()
                .map(connectMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConnectionResponse> getMyConnections() {

        User currentUser =
                SecurityUtils.getCurrentUser();

        return connectionRepository
                .findConnectionsByStatus(
                        currentUser,
                        ConnectionStatus.ACCEPTED
                )
                .stream()
                .map(connectMapper::toResponse)
                .toList();
    }



}