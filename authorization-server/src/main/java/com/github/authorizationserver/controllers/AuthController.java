package com.github.authorizationserver.controllers;

import com.github.authorizationserver.dto.requests.CreateUserRequest;
import com.github.authorizationserver.dto.responses.MessageResponse;
import com.github.authorizationserver.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> createUser(@RequestBody CreateUserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createNewUser(request));
    }
}
