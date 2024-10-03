package com.github.authorizationserver.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    @JsonIgnore
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    private boolean accountNonExpired= false;
    private boolean accountNonLocked = false;
    private boolean credentialsNonExpired= false;
    private boolean enabled= false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return !enabled;
    }
}
