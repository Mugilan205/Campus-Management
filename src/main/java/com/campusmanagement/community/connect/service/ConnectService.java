package com.campusmanagement.community.connect.service;

import com.campusmanagement.community.connect.dto.response.ConnectionResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ConnectService {

    ConnectionResponse sendConnectionRequest(Long receiverId);

    ConnectionResponse acceptConnectionRequest(Long requestId);

    ConnectionResponse rejectConnectionRequest(Long requestId);

    void cancelConnectionRequest(Long requestId);

    void removeConnection(Long connectionId);

    List<ConnectionResponse> getIncomingRequests();

    @Transactional(readOnly = true)
    List<ConnectionResponse> getOutgoingRequests();

    List<ConnectionResponse> getMyConnections();


}