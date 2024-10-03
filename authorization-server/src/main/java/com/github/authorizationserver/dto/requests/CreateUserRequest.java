package com.github.authorizationserver.dto.requests;

import java.util.List;

public record CreateUserRequest(String username, String password, List<String> roles) {
}
