package com.pyramid.tech.domain.registration.service;

import com.pyramid.tech.domain.registration.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Suvorov Vassilievitch
 * Date: 23/12/2024
 * Time: 22:27
 * Project Name: pyramid-game-security-oauth2
 */
@Service
public class JpaDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User name not found: " + username));
    }
}
