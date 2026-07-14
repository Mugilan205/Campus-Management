package com.campusmanagement.auth.service;

import com.campusmanagement.auth.dto.request.RegisterRequest;
import com.campusmanagement.auth.dto.response.RegisterResponse;

public interface AuthService {// in future u csn implement oauth or any auth by implementing this interface

    RegisterResponse register(RegisterRequest request);

}