package com.github.authorizationserver.entities;

import com.github.authorizationserver.enums.ERole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements GrantedAuthority {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private ERole role;

    @Override
    public String getAuthority() {
        return role.name();
    }
}
