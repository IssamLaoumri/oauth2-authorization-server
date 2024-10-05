package com.github.authorizationserver.controllers;

import com.github.authorizationserver.dto.requests.CreateClientRequest;
import com.github.authorizationserver.dto.responses.MessageResponse;
import com.github.authorizationserver.services.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Slf4j
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> createNewClient(@RequestBody CreateClientRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(request));
    }
}
