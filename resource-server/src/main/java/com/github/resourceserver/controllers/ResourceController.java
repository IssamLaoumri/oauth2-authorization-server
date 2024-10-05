package com.github.resourceserver.controllers;

import com.github.resourceserver.dto.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @GetMapping("/user")
    public ResponseEntity<MessageResponse> userResource(){
        return ResponseEntity.ok(new MessageResponse("Hello from User"));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> adminResource(){
        return ResponseEntity.ok(new MessageResponse("Hello from Admin"));
    }
}
