package com.github.authorizationserver.serviceImpls;

import com.github.authorizationserver.dto.requests.CreateClientRequest;
import com.github.authorizationserver.dto.responses.MessageResponse;
import com.github.authorizationserver.entities.Client;
import com.github.authorizationserver.repositories.ClientRepository;
import com.github.authorizationserver.services.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    private Client clientFromRequest(CreateClientRequest request) {
        return Client.builder()
                .clientSecret(passwordEncoder.encode(request.getClientSecret()))
                .clientAuthenticationMethods(request.getClientAuthenticationMethods())
                .scopes(request.getScopes())
                .clientId(request.getClientId())
                .authorizationGrantTypes(request.getAuthorizationGrantTypes())
                .redirectUris(request.getRedirectUris())
                .requireProofKey(request.isRequireProofKey())
                .build();
    }

    @Override
    public MessageResponse createClient(CreateClientRequest request) {
        Client client = clientFromRequest(request);
        clientRepository.save(client);
        return new MessageResponse("Client with id : "+client.getClientId()+" created successfully");
    }

    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    public RegisteredClient findById(String id) {
        Client client = clientRepository.findByClientId(id).orElseThrow(()-> new RuntimeException("Client not found"));
        return Client.toRegisteredClient(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Client client = clientRepository.findByClientId(clientId).orElseThrow(()-> new RuntimeException("Client not found"));
        return Client.toRegisteredClient(client);
    }
}
