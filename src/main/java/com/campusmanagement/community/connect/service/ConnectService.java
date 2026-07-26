package com.campusmanagement.community.connect.service;

import com.campusmanagement.community.connect.dto.response.ConnectionResponse;

public interface ConnectService {

    ConnectionResponse sendConnectionRequest(Long receiverId);

}