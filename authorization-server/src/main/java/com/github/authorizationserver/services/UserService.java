package com.github.authorizationserver.services;

import com.github.authorizationserver.dto.requests.CreateUserRequest;
import com.github.authorizationserver.dto.responses.MessageResponse;

public interface UserService {
    MessageResponse createNewUser(CreateUserRequest request);
}
