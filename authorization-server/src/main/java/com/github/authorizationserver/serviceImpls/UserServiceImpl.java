package com.github.authorizationserver.serviceImpls;

import com.github.authorizationserver.dto.requests.CreateUserRequest;
import com.github.authorizationserver.dto.responses.MessageResponse;
import com.github.authorizationserver.entities.Role;
import com.github.authorizationserver.entities.User;
import com.github.authorizationserver.enums.ERole;
import com.github.authorizationserver.repositories.RoleRepository;
import com.github.authorizationserver.repositories.UserRepository;
import com.github.authorizationserver.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MessageResponse createNewUser(CreateUserRequest request) {
        User newUser = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .build();
        Set<Role> roles = new HashSet<>();
        request.roles().forEach(r -> {
            Role role = roleRepository.findByRole(ERole.valueOf(r)).orElseThrow(()->
                new RuntimeException("Role "+r+" not found")
            );
            roles.add(role);
        });
        newUser.setRoles(roles);
        userRepository.save(newUser);
        return new MessageResponse("User : "+newUser.getUsername()+" created successfully.");
    }
}
