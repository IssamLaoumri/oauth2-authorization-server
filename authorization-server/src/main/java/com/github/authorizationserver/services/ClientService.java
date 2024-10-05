package com.github.authorizationserver.services;

import com.github.authorizationserver.dto.requests.CreateClientRequest;
import com.github.authorizationserver.dto.responses.MessageResponse;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

public interface ClientService extends RegisteredClientRepository {
    MessageResponse createClient(CreateClientRequest request);
}
