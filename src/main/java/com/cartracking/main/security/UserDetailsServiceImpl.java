package com.cartracking.main.security;

import com.cartracking.main.entities.User;
import com.cartracking.main.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    private SecurityUserFactory securityUserFactory;

    @Autowired
    public UserDetailsServiceImpl(UserService userService, SecurityUserFactory userFactory) {
        this.userService = userService;
        this.securityUserFactory = userFactory;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username);
        if (user == null) {
            System.out.print("user is null");
            throw new UsernameNotFoundException("no user found with email " + username);
        }

        return securityUserFactory.create(user);
    }
}
