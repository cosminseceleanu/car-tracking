package com.cartracking.main.security;

import com.cartracking.main.entities.Role;
import com.cartracking.main.entities.User;
import com.cartracking.main.security.model.SecurityUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SecurityUserFactory {
    public SecurityUser create(User user) {
        Set<Role> roles = user.getRoles();

        return new SecurityUser(user.getId(),
                user.getEmail(),
                user.getPassword(),
                getGrantedAuthorities(roles)
        );
    }

    private List<GrantedAuthority> getGrantedAuthorities(Set<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return authorities;
    }
}
